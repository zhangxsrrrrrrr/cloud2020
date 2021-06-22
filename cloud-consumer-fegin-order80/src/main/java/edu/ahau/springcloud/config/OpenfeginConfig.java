package edu.ahau.springcloud.config;

import feign.Feign;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangxuna
 * @create: 2021-06-22 20:45
 * @description: TODO
 */
@Configuration
public class OpenfeginConfig {
    @Bean
    Logger.Level feginLoggerLevel(){
        return Logger.Level.FULL;
    }
}
