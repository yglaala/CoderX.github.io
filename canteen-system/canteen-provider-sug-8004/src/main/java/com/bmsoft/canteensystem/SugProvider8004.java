package com.bmsoft.canteensystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chengpeng
 * @create 2018-10-15 17:34
 */
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@SpringBootApplication
public class SugProvider8004 {
    public static void main(String[] args) {
        SpringApplication.run(SugProvider8004.class, args);
    }
}
