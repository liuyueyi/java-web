package com.mogu.hui.study.reflect;

import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 通过反射调用对象的方法
 * Created by yihui on 16/1/20.
 */
public class MethodInvoke {
    /**
     * 通过换入类名，方法名，参数列表，来调用方法
     *
     * @param className
     * @param methodName
     * @param args
     * @return
     */
    public static Object invoke(String className, String methodName, Object... args) throws Exception {
        if (StringUtils.isEmpty(className)) {
            throw new IllegalArgumentException("class name is empty!");
        }

        if (StringUtils.isEmpty(methodName)) {
            throw new IllegalArgumentException("method name is empty!");
        }

        // get the object
        Class clz = Class.forName(className);
        Object object = clz.newInstance();

        // get the method
        // FIXME 根据方法名和参数列表获取方法，当方法的参数是基本数据类型是，会出现找不到方法的问题
        // FIXME 如 add(int a, int b) 方法，即便传入的方法名为add, 参数为[10,20], 也是有问题的
        Class[] argClzs = new Class[args.length];
        int i = 0;
        for (Object arg : args) {
            argClzs[i++] = arg.getClass();
        }
        Method method = clz.getMethod(methodName, argClzs);


        // 执行方法
        return method.invoke(object, args);
    }

    /**
     * 通过换入类名，方法名，参数列表，来调用方法
     *
     * @param className
     * @param methodName
     * @param args
     * @return
     */
    public static Object invokeV2(String className, String methodName, Object... args) throws Exception {
        if (StringUtils.isEmpty(className)) {
            throw new IllegalArgumentException("class name is empty!");
        }

        if (StringUtils.isEmpty(methodName)) {
            throw new IllegalArgumentException("method name is empty!");
        }

        // get the object
        Class clz = Class.forName(className);
        Object object = clz.newInstance();

        // get the method
        // 遍历所有的方法，根据方法名和参数对象确定
        Method[] methods = clz.getDeclaredMethods();
        Method selectMethod = null;
        for (Method method : methods) {
            if (!methodName.equals(method.getName())
                    || args.length != method.getParameterTypes().length) {
                continue;
            }

            Type[] types1 = method.getParameterTypes();
            Type[] types2 = method.getGenericParameterTypes();


            // FIXME 需要精确匹配
            selectMethod = method;
        }

        if (selectMethod != null) { // 执行方法
            return selectMethod.invoke(object, args);
        } else {
            throw new NoSuchMethodException("no such method: " + methodName +
                    " found in: " + className);
        }
    }
}
