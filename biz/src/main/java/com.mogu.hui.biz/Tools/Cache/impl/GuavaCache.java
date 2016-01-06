package com.mogu.hui.biz.Tools.Cache.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mogu.hui.biz.Tools.Cache.IMethodCache;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by yihui on 16/1/6.
 */
public class GuavaCache implements IMethodCache {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GuavaCache.class);

    private Cache<String, String> cache;

    private final Long EXPIRE_HOUR = 24L;
    private final Long MAX_SIZE = 1000L;

    public GuavaCache() throws Exception {
        this.init();
    }

    @Override
    public Object getObject(String key, Class<?> clz) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key is null");
        }

        if (clz == null) {
            throw new IllegalArgumentException("calz is null");
        }

        try{
            String result = cache.getIfPresent(key);
            if (result == null) {
                return null;
            }

            Object obj = JSON.parseObject(result, clz); // 分开的原则是方便debug，查看变量的值
            return obj;
        } catch (Exception e) {
            logger.warn("get object from cache error! key: {} ", key, e);
            return null;
        }
    }

    /**
     * 加入缓存
     * @param key
     * @param value
     * @param expireSeconds   时间在初始化guava cache的时候，就指定了
     * @return
     */
    @Override
    public boolean setObject(String key, Object value, int expireSeconds) {
        if (StringUtils.isEmpty(key) || value == null || expireSeconds <= 0) {
            throw new IllegalArgumentException("parameter key || value || expireSeconds error!");
        }

        try {
            String varStr = JSON.toJSONString(value);
            cache.put(key, varStr);
            return true;
        } catch (Exception e) {
            logger.warn("set object into cache error! key{} value{}", key, value, e);
            return false;
        }
    }

    @Override
    public boolean expire(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key is empty!");
        }

        try {
            cache.invalidate(key);
            return true;
        } catch (Exception e) {
            logger.warn("free cache object error! key:{}", key, e);
            return false;
        }
    }


    private void init() throws Exception {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_HOUR, TimeUnit.HOURS)
                .maximumSize(MAX_SIZE).build();
    }
}
