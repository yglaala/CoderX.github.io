package com.bmsoft.canteensystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author chengpeng
 * @create 2018-10-15 9:21
 */
@EnableEurekaServer //EurekaServer服务器端启动类，接受其他微服务注册进来
@SpringBootApplication
public class EurekaServer8001Application {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer8001Application.class,args);
    }
}
