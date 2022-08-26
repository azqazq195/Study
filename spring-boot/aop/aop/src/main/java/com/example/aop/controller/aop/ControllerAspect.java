package com.example.aop.controller.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class ControllerAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void request() {
    }

    @Around("request()")
    public Object logging(ProceedingJoinPoint pjp) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("[{}] {}", request.getMethod(), request.getRequestURI());

        log.info(joinPoint.toShortString());
        for (Object obj : joinPoint.getArgs()) {
            log.info("{} : {}", obj.getClass().getSimpleName(), obj);
        }
    }


//    @Before("request()")
//    public void before(JoinPoint joinPoint) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        log.info("[{}] {}", request.getMethod(), request.getRequestURI());
//
//        log.info(joinPoint.toShortString());
//        for (Object obj : joinPoint.getArgs()) {
//            log.info("{} : {}", obj.getClass().getSimpleName(), obj);
//        }
//    }
//
//    @AfterReturning(value = "request()", returning = "obj")
//    public void afterReturn(Object obj) {
//        log.info(obj.toString());
//    }

}
