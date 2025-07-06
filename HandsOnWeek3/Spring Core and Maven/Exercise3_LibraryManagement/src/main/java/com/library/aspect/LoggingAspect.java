package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {

    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();  

        long end = System.currentTimeMillis();
        System.out.println("LOG Method " + joinPoint.getSignature().getName() +
                           " executed in " + (end - start) + " ms");

        return result;
    }
}
