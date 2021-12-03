package com.example.com_guigu_service1.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/config")
@RefreshScope
public class Config {

    @Value("${useLocalCache:local}")
    private String useLocalCache;

    @RequestMapping("/get")
    @SentinelResource("get")
    public String get() {
        System.out.println(useLocalCache);
        return useLocalCache;
    }
}
