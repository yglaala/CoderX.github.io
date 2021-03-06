package com.bmsoft.canteensystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author chengpeng
 * @create 2018-10-17 19:23
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.bmsoft.canteensystem"})
@ComponentScan("com.bmsoft.canteensystem")
@EnableRedisHttpSession
public class ShoppingCarConsumer8003 {


    public static void main(String[] args) {
        SpringApplication.run(ShoppingCarConsumer8003.class,args);
    }
}
