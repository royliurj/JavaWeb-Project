package com.roy.website.common.version;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;


//TODO 参考 - 微服务API版本控制 - https://blog.csdn.net/flyingnet/article/details/80448810

/**
 * @Author: Roy
 * @Date: 2019/5/6 14:35
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {

    /**
     * version
     *
     * @return
     */
    int value();
}
