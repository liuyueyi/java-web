package com.mogu.hui.biz.Tools.Cache.impl;

import com.alibaba.fastjson.JSON;
import com.mogu.hui.biz.Tools.Cache.IMethodCache;
import org.springframework.util.StringUtils;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yihui on 16/1/6.
 */
public class RedisCache implements IMethodCache {
    private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RedisCache.class);

    private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池

    public RedisCache() throws Exception {
        this.init();
    }

    private void init() throws Exception {
        initialPool();
        initialShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();
    }

    /**
     * 初始化非切片池
     */
    private void initialPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config,"127.0.0.1",6379);
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    @Override
    public Object getObject(String key, Class<?> clz) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key is blank!");
        }

        if (clz == null) {
            throw new IllegalArgumentException("clz is null!");
        }

        try {
            String obj = jedis.get(key);
            Object result = JSON.parseObject(obj, clz);
            return result;
        } catch (Exception e) {
            logger.warn("get object from redis cache error! key{} ");
        }
        return null;
    }

    @Override
    public boolean setObject(String key, Object value, int expireSeconds) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key is blank!");
        }

        if (value == null) {
            throw new IllegalArgumentException("value is null!");
        }

        if (expireSeconds <= 0) {
            throw  new IllegalArgumentException("cache time is less than 0!");
        }

        try {
            String obj = JSON.toJSONString(value);
            String ans = jedis.set(key, obj);
            if (!StringUtils.isEmpty(ans)) {
                jedis.expire(key, expireSeconds);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.warn("set object into redis cache error! key{} value{}", key, value, e);
            return false;
        }
    }

    @Override
    public boolean expire(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key is blank!");
        }

        try {
            if (jedis.exists(key)) {
                long time = jedis.del(key);
                return time >= 0;
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("The cell is not exists! key {}", key);
                }
                return false;
            }
        } catch (Exception e) {
            logger.warn("del cache error! key {}" , key);
            return false;
        }
    }
}
