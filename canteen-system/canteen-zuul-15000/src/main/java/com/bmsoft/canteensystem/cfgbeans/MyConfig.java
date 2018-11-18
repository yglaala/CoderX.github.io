package com.bmsoft.canteensystem.cfgbeans;

import com.bmsoft.canteensystem.filter.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1200, redisFlushMode = RedisFlushMode.IMMEDIATE)
public class MyConfig {

    @Bean
    public LoginFilter getLoginFilter() {
        return new LoginFilter();
    }
//
//    @Bean
//    public PostFilter getPostFilter() {
//        return new PostFilter();
//    }
}
