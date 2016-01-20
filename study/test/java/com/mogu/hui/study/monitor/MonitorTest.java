package com.mogu.hui.study.monitor;

import com.mogu.hui.study.monitor.biz.BaseBusiness;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yihui on 16/1/19.
 */
public class MonitorTest {
    BaseBusiness baseBusiness;

    @Before
    public void setUp() {
        baseBusiness = new BaseBusiness();
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:service.xml");
        //获取service组件
        baseBusiness = context.getBean("baseBusiness", BaseBusiness.class);
    }

    @Test
    public void rtTest() {
        int ans = baseBusiness.add(10);
        System.out.println("The result is : " + ans);
        ans = baseBusiness.sub(10);
        System.out.println(ans);
        ans = baseBusiness.mul(10);
        System.out.println(ans);
    }
}
