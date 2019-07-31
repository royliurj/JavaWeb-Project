package com.roy.website.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Roy
 * @Date: 2019/7/31 10:58
 */
@Component
public class American {
    private Logger logger = LoggerFactory.getLogger(American.class);

    public American() {
        super();
        logger.info("American ==> American method : 正在生成一个American实例");
    }

    @PersonAnnotation("American")//该注解是用来定义切点
    public String say(String name) {
        logger.info("American ==> say method : say {}", name);
        return name + " hello, CGLIB implement AOP";
    }

    public void eat(String food) {
        logger.info("American ==> eat method : eat {}", food);
    }

}
