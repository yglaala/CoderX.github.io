package com.bmsoft.canteensystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
<<<<<<< HEAD:canteen-provider-order-7001/src/main/java/com/bmsoft/canteensystem/ProviderOrder7001_App.java
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)
public class ProviderOrder7001_App {

=======
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)//使用redis的session
public class ProviderCollocationOfDishes9002_App {
>>>>>>> origin/master:canteen-provider-collocationofdishes-9002/src/main/java/com/bmsoft/canteensystem/ProviderCollocationOfDishes9002_App.java
    public static void main(String[] args) {
        SpringApplication.run(ProviderCollocationOfDishes9002_App.class,args);
    }
}
