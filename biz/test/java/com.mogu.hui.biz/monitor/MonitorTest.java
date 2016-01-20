package com.mogu.hui.biz.monitor;

import com.mogu.hui.biz.CalculateService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yihui on 16/1/19.
 */
public class MonitorTest {
    CalculateService calculateService;

    @Before
    public void setUp() {
        ApplicationContext apc = new ClassPathXmlApplicationContext("classpath:/service.xml");
        calculateService = apc.getBean("calculateService", CalculateService.class);
    }

    @Test
    public void rtTest() {
        int ans = calculateService.add(10);
        System.out.println(ans);

        ans = calculateService.tt(20);
    }

}
