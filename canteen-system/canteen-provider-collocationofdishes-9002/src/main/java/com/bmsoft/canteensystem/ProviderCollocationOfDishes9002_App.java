package com.bmsoft.canteensystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableEurekaClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)//使用redis的session
public class ProviderCollocationOfDishes9002_App {
    public static void main(String[] args) {
        SpringApplication.run(ProviderCollocationOfDishes9002_App.class,args);
    }
}
