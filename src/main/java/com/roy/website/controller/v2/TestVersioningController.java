package com.roy.website.controller.v2;

import com.roy.website.common.version.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Roy
 * @Date: 2019/5/6 14:42
 */

@ApiVersion(2)
@RequestMapping("/{api_version}")
@RestController("TestVersioningController-v2")
public class TestVersioningController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello v2";
    }
}
