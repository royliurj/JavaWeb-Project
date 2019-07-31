package com.roy.website.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: Roy
 * @Date: 2019/7/31 10:59
 */
@Configuration
@ComponentScan("com.roy.website.aop")
//开启AspectJ注解
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfiguration {
}
