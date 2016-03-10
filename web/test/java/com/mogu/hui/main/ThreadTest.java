package com.mogu.hui.main;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yihui on 16/3/10.
 */
public class ThreadTest {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ThreadTest.class);

    private ExecutorService executorService = new ThreadPoolExecutor(10,
            20,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(10),new DefaultThreadFactory("thread-test"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private int calculate(int a) {
        return a * a;
    }

    @Test
    public void testGetItemDsr() {
        final int add = 10;
        for (int i = 0; i < 20; i++ ) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    int ans = calculate(add);
                    logger.error("The calculate result is : {}", ans);
                }
            };
            executorService.submit(runnable);
        }
        logger.error("over");
    }
}
