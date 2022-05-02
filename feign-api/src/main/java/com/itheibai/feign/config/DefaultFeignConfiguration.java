package com.itheibai.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

// Feign自定义配置方式二
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
