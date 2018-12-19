package com.ygl.canteen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD:canteen-zuul-15000/src/main/java/com/bmsoft/canteensystem/Zuul15000_App.java
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)
public class Zuul15000_App {
=======

@SpringBootApplication
public class CanteenPic100_App {
>>>>>>> origin/master:cateen-pic-1000/src/main/java/com/ygl/canteen/CanteenPic100_App.java

    public static void main(String[] args) {
        SpringApplication.run(CanteenPic100_App.class,args);
    }
}
