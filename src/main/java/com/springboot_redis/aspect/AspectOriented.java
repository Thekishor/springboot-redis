package com.springboot_redis.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectOriented {

    @Pointcut("execution(* com.springboot_redis.controller.*.*(..))")
    public void loggingPointCut() {
    }

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint) {
        log.info("Before Method Invoked:{}", joinPoint.getSignature());
    }

    @After("loggingPointCut()")
    public void after(JoinPoint joinPoint) {
        log.info("After Method Invoked:{}", joinPoint.getSignature());
    }

    @Around("loggingPointCut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Aspect log called");
        Object proceed = joinPoint.proceed();
        System.out.println("Aspect after log called");
        return proceed;
    }

}
