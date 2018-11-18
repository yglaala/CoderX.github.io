package com.bmsoft.canteensystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author liugaoyang
 * @Date 2018/10/22 14:23
 */
@SpringBootApplication
@EnableEurekaClient//本服务启动后回自动注入进eureka服务中
@EnableFeignClients(basePackages = {"com.bmsoft.canteensystem"})//注解开启FeignCleint
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)//使用redis的session
public class ConsumerCollocationOfDishes9012_App {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerCollocationOfDishes9012_App.class,args);
    }
}
