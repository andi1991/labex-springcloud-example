package com.example.com_guigu_service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class ComGuiguService1Application {

    public static void main(String[] args) throws InterruptedException{
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ComGuiguService1Application.class, args);
        System.out.println("service1 start success");
    }
}
