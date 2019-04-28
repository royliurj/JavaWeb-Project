package com.roy.website.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @Author: Roy
 * @Date: 2019/4/28 10:20
 */
@RestController
@RequestMapping("/test")
public class TestController {

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public String exception(Exception ex){
//        return this.getClass().getSimpleName() + " : " + ex.getMessage();
//    }

    /**
     * 400
     * @param userId
     * @return
     */
    @GetMapping(value = "/query")
    public String query(@RequestParam("uid") long userId){
        return userId+"";
    }

    /**
     * 500
     * @return
     */
    @GetMapping(value = "/calc")
    public String calc(){
        int a = 2/0;
        return "";
    }
}
