package com.example.com_guigu_service1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @RequestMapping("/status")
    public String get() {
        return "success";
    }

    @Value("${name}")
    private String name;

    @RequestMapping("/getName")
    public String getName() {
        return name;
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}




