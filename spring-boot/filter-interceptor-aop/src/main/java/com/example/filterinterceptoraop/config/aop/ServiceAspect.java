package com.example.filterinterceptoraop.config.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ServiceAspect {

    @Pointcut("execution(* com.example.filterinterceptoraop.service..*.*(..))")
    public void request() {
    }

    @Before("request()")
    public void before(JoinPoint joinPoint) {
    log.info("[AOP] before '{}' service", getMethodName(joinPoint));
    }

    @After("request()")
    public void after(JoinPoint joinPoint) {
        log.info("[AOP] after '{}' service", getMethodName(joinPoint));
    }

    @AfterReturning("request()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("[AOP] after returning '{}' service", getMethodName(joinPoint));
    }

    @AfterThrowing("request()")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("[AOP] after throwing '{}' service", getMethodName(joinPoint));
    }

    @Around("request()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("[AOP] around before '{}' service", getMethodName(pjp));
        Object result = pjp.proceed();
        log.info("[AOP] around after '{}' service", getMethodName(pjp));
        return result;
    }

    private String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }
}
