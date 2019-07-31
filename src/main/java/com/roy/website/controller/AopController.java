package com.roy.website.controller;

import com.roy.website.aop.American;
import com.roy.website.aop.Person;
import com.roy.website.aop.PersonAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Roy
 * @Date: 2019/7/31 11:00
 */
@RestController
@RequestMapping("/aop")
public class AopController {
    private Logger logger = LoggerFactory.getLogger(AopController.class);

    @Autowired
    private Person chinese;
    @Autowired
    private American american;

    @PersonAnnotation("Controller")
    @GetMapping("/talk")
    public String talk() {
        chinese.say("中国人说汉语");
        american.say("American say english");

        logger.info("AopImplementController ==> talk method : {}", chinese.getClass());
        logger.info("AopImplementController ==> talk method : {}", american.getClass());

        return "success";
    }
}
