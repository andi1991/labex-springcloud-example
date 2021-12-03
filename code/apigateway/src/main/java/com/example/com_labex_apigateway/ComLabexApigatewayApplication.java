package com.example.com_labex_apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class ComLabexApigatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(ComLabexApigatewayApplication.class, args);
        System.out.println("api gateway start success");
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/csdn")
                        .uri("https://blog.csdn.net"))
                .build();
    }
}
