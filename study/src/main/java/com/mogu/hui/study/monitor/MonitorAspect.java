package com.mogu.hui.study.monitor;

import com.mogu.hui.study.monitor.anno.RT;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by yihui on 16/1/19.
 */
@Component
@Aspect
public class MonitorAspect {
    private final static org.slf4j.Logger log = LoggerFactory.getLogger(MonitorAspect.class);

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.mogu.hui.study..*(..))")
    public void aspect() {
    }

    /*
     * 配置前置通知,使用在方法aspect()上注册的切入点
     * 同时接受JoinPoint切入点对象,可以没有该参数
     */
    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
//        log.info("before " + joinPoint);
    }

    //配置后置通知,使用在方法aspect()上注册的切入点
    @After("aspect()")
    public void after(JoinPoint joinPoint) {
//        log.info("after " + joinPoint);
    }

    //配置环绕通知,使用在方法aspect()上注册的切入点
    @Around("aspect()")
    public Object around(JoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        try {
            boolean calRt = false;

            String name = joinPoint.getSignature().getName();
            Class clz = joinPoint.getSignature().getDeclaringType();

            Object[] args = joinPoint.getArgs();
            Class[] paraz = new Class[args.length];
            for (int i =0 , len = args.length; i < len; i++) {
                paraz[i] = args[i].getClass();
            }
            Method method = clz.getMethod(name, paraz);
            if (method.getAnnotation(RT.class) != null) {
                calRt = true;
            }
            long end = System.currentTimeMillis();
            log.info("判断 前置获取耗时: {}", end-start);

            start = System.currentTimeMillis();
            Object result = ((ProceedingJoinPoint) joinPoint).proceed();
            end = System.currentTimeMillis();
            if (calRt) {
                log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
            }
//            System.out.println(1/0);
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
        }
        return null;
    }

    //配置后置返回通知,使用在方法aspect()上注册的切入点
    @AfterReturning("aspect()")
    public void afterReturn(JoinPoint joinPoint) {
//        log.info("afterReturn " + joinPoint);
    }

    //配置抛出异常后通知,使用在方法aspect()上注册的切入点
    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
//        log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
    }
}
