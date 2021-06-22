package edu.ahau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: zhangxuna
 * @create: 2021-06-02 21:33
 * @description: TODO
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer7002 {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EurekaServer7002.class);
    }
}
