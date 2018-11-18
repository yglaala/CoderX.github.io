package com.bmsoft.canteensystem;
/**
 * USER-CONSUMER 主启动类
 * @Author liugaoyang
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableEurekaClient//本服务启动后回自动注入进eureka服务中
@EnableFeignClients(basePackages = {"com.bmsoft.canteensystem"})//注解开启FeignCleint
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)//使用redis的session
public class ConsumerUser9011_App {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerUser9011_App.class,args);
    }
}
