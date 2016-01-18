package com.mogu.hui.study;

import com.mogu.hui.study.Constants.RoleLevel;
import com.mogu.hui.study.annotation.Role;
import com.mogu.hui.study.aop.CglibProxy;
import com.mogu.hui.study.aop.ReflectProxy;
import com.mogu.hui.study.base.Cell;
import com.mogu.hui.study.base.UserBean;
import com.mogu.hui.study.base.UserBeanImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;

/**
* Created by yihui on 16/1/9.
*/
public class AopTest {
    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AopTest.class);

    @Role
    UserBeanImpl defaultUserBean;

    @Role(level = RoleLevel.ROOT)
    UserBeanImpl rootUserBean;

    @Before
    public void setup() {
        defaultUserBean = new UserBeanImpl();
        rootUserBean  = new UserBeanImpl();
    }

    @Test
    public void aopTest() {
        CglibProxy proxy = new CglibProxy();
        Cell cell = (Cell) proxy.createTargetObject(new Cell());
        long ans = cell.nTimes(1000);
        logger.info("The ans is : ", ans);
    }

    @Test
    public void aopTest2() {
        defaultUserBean = new UserBeanImpl();
        ReflectProxy proxy = new ReflectProxy();
        proxy.createTargetObject(defaultUserBean);

        // 代理对象
        UserBean userBean = (UserBean) Proxy.newProxyInstance(defaultUserBean.getClass().getClassLoader(),
                defaultUserBean.getClass().getInterfaces(),
                proxy);
        userBean.addUser();


        // 下一个测试

        rootUserBean = new UserBeanImpl();
        proxy.createTargetObject(rootUserBean);
        userBean = (UserBean) Proxy.newProxyInstance(rootUserBean.getClass().getClassLoader(),
                rootUserBean.getClass().getInterfaces(), proxy);
        userBean.addUser();



        proxy.createTargetObject(this);
        userBean = (UserBean) Proxy.newProxyInstance(rootUserBean.getClass().getClassLoader(),
                rootUserBean.getClass().getInterfaces(), proxy);
        userBean.addUser();

        UserBeanImpl u = new UserBeanImpl();
        u.addUser();
    }

    @Test
    public void anoTest() {
        Annotation[] fields = AopTest.class.getDeclaredAnnotations();
        for (Annotation ano: fields) {
            System.out.println(ano + "");
        }
        System.out.println("over");
    }


    @Test
    public void fieldAnoTest() {
        AopTest aopTest = new AopTest();

    }
}
