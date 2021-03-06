package com.mogu.hui.biz.tools.cache.impl;

import com.alibaba.fastjson.JSON;
import com.mogu.hui.biz.tools.cache.IMethodCache;
import org.springframework.util.StringUtils;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    /**
     * jedis常用方法测试
     */
    public void KeyOperate()
    {
        System.out.println("======================key==========================");
        // 清空数据
        System.out.println("清空库中所有数据："+jedis.flushDB());
        // 判断key否存在
        System.out.println("判断key999键是否存在："+shardedJedis.exists("key999"));
        String ans = shardedJedis.set("key000", "value000");
        System.out.println("新增key001,value001键值对："+shardedJedis.set("key001", "value001"));
        System.out.println("判断key001是否存在："+shardedJedis.exists("key001"));
        // 输出系统中所有的key
        System.out.println("新增key002,value002键值对："+shardedJedis.set("key002", "value002"));
        System.out.println("系统中所有键如下：");
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
        // 删除某个key,若key不存在，则忽略该命令。
        System.out.println("系统中删除key002: "+jedis.del("key002"));
        System.out.println("判断key002是否存在："+shardedJedis.exists("key002"));
        // 设置 key001的过期时间
        System.out.println("设置 key001的过期时间为5秒:"+jedis.expire("key001", 5));
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
        }
        // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
        // 移除某个key的生存时间
        System.out.println("移除key001的生存时间："+jedis.persist("key001"));
        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
        // 查看key所储存的值的类型
        System.out.println("查看key所储存的值的类型："+jedis.type("key001"));
        /*
         * 一些其他方法：1、修改键名：jedis.rename("key6", "key0");
         *             2、将当前db的key移动到给定的db当中：jedis.move("foo", 1)
         */
    }
}
