package com.example.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/withoutParam")
    public String withoutArgs() {
        return "hello world.";
    }

    @RequestMapping("/withParam")
    public String withArgs(String param) {
        return param;
    }

}
