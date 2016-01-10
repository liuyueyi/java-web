package com.mogu.hui.study.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
* Created by yihui on 16/1/9.
*/
public class CglibProxy implements MethodInterceptor {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CglibProxy.class);
    private Object targetObject;

    public Object createTargetObject(Object object) {
        this.targetObject = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.targetObject.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = method.invoke(targetObject, args);
        long end = System.currentTimeMillis();

        logger.info("class:{} method: {} call cost : " + (end - start), method.getDeclaringClass(), method.getName());
        return result;
    }
}
