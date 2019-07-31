package com.roy.website.aop;

import java.lang.annotation.*;

/**
 * @Author: Roy
 * @Date: 2019/7/31 10:58
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface PersonAnnotation {

    /**
     * 业务名称
     * @return
     */
    String value() default "";
}
