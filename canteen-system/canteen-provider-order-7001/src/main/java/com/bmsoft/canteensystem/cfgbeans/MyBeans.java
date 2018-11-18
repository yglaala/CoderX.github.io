package com.bmsoft.canteensystem.cfgbeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class MyBeans {

    /*@Bean
    public PageHelper pageHelper() {
        //System.out.println("配置");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);

        new SqlSessionFactoryBean().setPlugins(new Interceptor[]{pageHelper});

        return pageHelper;
    }*/

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
