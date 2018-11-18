package com.bmsoft.canteensystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author guoguo
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderOrder7001_App {

    public static void main(String[] args) {
        SpringApplication.run(ProviderOrder7001_App.class,args);
    }
}
