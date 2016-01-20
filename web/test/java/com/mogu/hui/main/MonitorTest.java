package com.mogu.hui.main;

import com.mogu.hui.web.modal.Calculate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yihui on 16/1/19.
 */
public class MonitorTest {
    Calculate calculateService;
    @Before
    public void setup() {
        ApplicationContext apc = new ClassPathXmlApplicationContext("classpath*:service.xml", "classpath*:webapp/*.xml");
        calculateService = apc.getBean("calculateService", Calculate.class);
    }

    @Test
    public void cla() {
        int ans = calculateService.tt(10);
        System.out.println(ans);
    }
}
