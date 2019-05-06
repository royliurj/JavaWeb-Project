package com.roy.website.controller.v1;

import com.roy.website.common.version.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Roy
 * @Date: 2019/5/6 14:41
 */
@ApiVersion(1)
@RequestMapping("/{api_version}")
@RestController("TestVersioningController-v1")
public class TestVersioningController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello v1";
    }
}
