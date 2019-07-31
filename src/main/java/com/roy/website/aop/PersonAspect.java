package com.roy.website.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Roy
 * @Date: 2019/7/31 10:55
 */
@Aspect
@Component
public class PersonAspect {
    private Logger logger = LoggerFactory.getLogger(PersonAspect.class);

    @Pointcut("@annotation(com.roy.website.aop.PersonAnnotation)")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        logger.info("PersonAspect ==> before method : {}", joinPoint.getSignature().getName());

    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint){
        logger.info("PersonAspect ==> after method : {}", joinPoint.getSignature().getName());
    }
}
