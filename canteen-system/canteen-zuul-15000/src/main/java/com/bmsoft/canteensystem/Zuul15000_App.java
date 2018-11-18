package com.bmsoft.canteensystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author guoguo
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)
public class Zuul15000_App {

    public static void main(String[] args) {
        SpringApplication.run(Zuul15000_App.class,args);
    }
}
