package edu.ahau.spirngcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: zhangxuna
 * @create: 2021-06-20 21:55
 * @description: TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsumerConsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerConsulMain80.class, args);
    }
}
