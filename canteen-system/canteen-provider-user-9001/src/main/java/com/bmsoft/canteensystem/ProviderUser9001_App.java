package com.bmsoft.canteensystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@SpringBootApplication
@EnableEurekaClient//本服务启动后回自动注入进eureka服务中
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)//使用redis的session
public class ProviderUser9001_App {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUser9001_App.class,args);
    }
}
