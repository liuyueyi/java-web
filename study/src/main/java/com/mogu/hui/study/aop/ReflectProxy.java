package com.mogu.hui.study.aop;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yihui on 16/1/9.
 */
public class ReflectProxy implements InvocationHandler {
    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ReflectProxy.class);

    private Object targetObject;

    public void createTargetObject(Object targetObject) {
        this.targetObject = targetObject;
        Class clz = targetObject.getClass();
        System.out.println(targetObject);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        Object result = null;
//        if (targetObject.getClass().isAnnotationPresent(Role.class)) {
//            // 有role注解，则需要进行权限判断
//            Role role = targetObject.getClass().getAnnotation(Role.class);
//            // custom 以上权限可以玩
//            if (role.level().getRoleValue() > RoleLevel.CUSTOM.getRoleValue()) {
//                result = method.invoke(targetObject, args);
//                logger.info("class:{} method:{} invoked!", method.getDeclaringClass(), method.getName());
//                return result;
//            } else {
//                logger.info("class:{} method:{} can not invoked because of role error!!", method.getDeclaringClass(), method.getName());
//                return false;
//            }
//        }
//
//        result = method.invoke(targetObject, args);
//        return result;
        Field[] fields = ReflectProxy.class.getDeclaredFields();

        logger.info("class:{} method:{} invoked!", method.getDeclaringClass(), method.getName());
        Object ans = method.invoke(targetObject, args);
        return ans;
    }
}
