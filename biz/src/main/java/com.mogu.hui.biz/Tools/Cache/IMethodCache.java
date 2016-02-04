package com.mogu.hui.biz.tools.cache;

/**
 * Created by yihui on 16/1/6.
 */
public interface IMethodCache {

    public Object getObject(String key, Class<?> clz);


    public boolean setObject(String key, Object value, int expireSeconds);

    public boolean expire(String key);
}
