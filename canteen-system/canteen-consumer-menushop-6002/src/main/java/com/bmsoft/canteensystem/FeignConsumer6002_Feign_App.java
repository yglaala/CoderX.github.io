package com.bmsoft.canteensystem;





import com.github.pagehelper.PageHelper;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.Properties;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.bmsoft.canteensystem"})
@ComponentScan("com.bmsoft.canteensystem")
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class FeignConsumer6002_Feign_App
{
    public static void main(String[] args)
    {
        SpringApplication.run(FeignConsumer6002_Feign_App.class, args);


    }
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    @Primary
    @Scope("prototype")
    public SpringFormEncoder  feignEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }
}
