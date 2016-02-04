package com.mogu.hui.study.reflect;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yihui on 16/1/20.
 */
public class MethodInvokeTest {

    public String add(String a, String b) {
        return a + b;
    }

    public int add(Integer a, Integer b) {
        return a + b;
    }

    /**
     * 如果实际工程中，出现这个与上面方法进行的多态，也真是呵呵了
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        return a + a + b + b;
    }

    public int add(Integer num) {
        if (num <= 1) {
            return 1;
        } else if (num == 2) {
            return 2;
        } else {
            return add(num - 1) + add(num - 2);
        }
    }

    @Test
    public void methodTest() {
        String className = "com.mogu.hui.study.reflect.MethodInvokeTest";
        String methodName = "add";
        try {
            Object ans = MethodInvoke.invokeV2(className, methodName, 10, 20);
            System.out.println("The ans is : " + ans);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void instanceTest() throws IllegalAccessException, InstantiationException {
        Class clz = int.class;
        Class clz2 = Integer.class;
        System.out.println(clz);
        System.out.println(clz.isAssignableFrom(clz2));
        System.out.println(clz2.isAssignableFrom(clz));
    }

    /**
     * 从配置文件中获取参数信息
     * @throws IOException
     */
    @Test
    public void propertiesTest() throws IOException {
        Properties pros = new Properties();
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = cld.getResourceAsStream("reflect.properties");
        pros.load(inputStream);
        String className = pros.getProperty("className");
        String methodName = pros.getProperty("methodName");
        String argStr = pros.getProperty("arguments");
        String[] args = argStr.split(",");
        try {
            Object ans = MethodInvoke.invokeV2(className, methodName, args);
            System.out.println("The ans is : " + ans);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
