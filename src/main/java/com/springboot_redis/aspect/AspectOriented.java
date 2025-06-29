package com.springboot_redis.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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

}
