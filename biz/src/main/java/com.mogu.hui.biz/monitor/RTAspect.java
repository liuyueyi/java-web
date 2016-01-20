package com.mogu.hui.biz.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面,用于计算RT
 * Created by yihui on 16/1/19.
 */
@Component
@Aspect
public class RTAspect {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RTAspect.class);

//    @Pointcut("execution(* com.mogu.hui..*(..))")
//    public void aspect() {
//
//
//    }
//
//    /**
//     * 注解计算方法的rt
//     * XXX 通过反射获取method的注解这个地方考虑如何优化一下
//     *
//     * @param joinPoint
//     * @return
//     * @throws Throwable
//     */
//    @Around("aspect()")
//    public Object around(JoinPoint joinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//        boolean calculateRt = false;
//        try {
//            Class clz = joinPoint.getSignature().getDeclaringType();
//            String name = joinPoint.getSignature().getName();
//            Object[] args = joinPoint.getArgs();
//            Class[] argClzs = new Class[args.length];
//            for (int i = 0, len = argClzs.length; i < len; i++) {
//                argClzs[i] = args[i].getClass();
//            }
//
//            Method method = clz.getMethod(name, argClzs);
//            if (method.getAnnotation(RT.class) != null) {
//                calculateRt = true;
//            }
//            long mid = System.currentTimeMillis();
//            logger.info("{} judge cost time: {}", joinPoint, mid - start);
//
//            Object result = ((ProceedingJoinPoint) joinPoint).proceed();
//            if (calculateRt) {
//                long end = System.currentTimeMillis();
//                logger.info("{} cost : {}", joinPoint, end - start);
//                return result;
//            }
//
//            return result;
//        } catch (Throwable e) {
//            throw e;
//        }
//    }

    @Pointcut("@annotation(com.mogu.hui.biz.monitor.annotation.RT)")
    public void rtAspect() {

    }

    /**
     * 直接拦截有RT注解的方法
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("rtAspect()")
    public Object aroundRt(JoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            logger.info("{} cost : {}", joinPoint, end - start);
            return result;
        } catch (Throwable e) {
            throw e;
        }
    }

}
