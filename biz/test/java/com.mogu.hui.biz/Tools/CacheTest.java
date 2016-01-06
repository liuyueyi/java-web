package com.mogu.hui.biz.Tools;

import com.mogu.hui.biz.Tools.Cache.impl.GuavaCache;
import com.mogu.hui.biz.Tools.Cache.impl.RedisCache;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yihui on 16/1/6.
 */
public class CacheTest {
    private GuavaCache guavaCache;
    private RedisCache redisCache;

    @Before
    public void setUp() {
        try {
            guavaCache = new GuavaCache();
            redisCache = new RedisCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void guavaTest() {
        String key = "Hello_1234";
        List<Map<String, String>> value = new ArrayList<>();
        value.add(new HashMap<String, String>(){
            {
                put("abc", "你好😄");
                put("efg", "嘿嘿");
            }
        });
        value.add(new HashMap<String, String>(){
            {
                put("123", "测试");
                put("456", "HOHO");
            }
        });

        guavaCache.setObject(key, value, 10);

        Object obj = guavaCache.getObject(key, List.class);
        System.out.println(obj);
    }

    @Test
    public void redisTest() {
        String key = "Hello_1234";
        List<Map<String, String>> value = new ArrayList<>();
        value.add(new HashMap<String, String>(){
            {
                put("abc", "你好😄");
                put("efg", "嘿嘿");
            }
        });
        value.add(new HashMap<String, String>(){
            {
                put("123", "测试");
                put("456", "HOHO");
            }
        });

        redisCache.setObject(key, value, 100);
        Object obj = redisCache.getObject(key, List.class);
        System.out.println(obj);
    }
}
