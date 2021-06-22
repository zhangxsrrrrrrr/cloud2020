package edu.ahau.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: zhangxuna
 * @create: 2021-05-17 23:28
 * @description: TODO
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = {"edu.ahau.springcloud.dao"})
public class PaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class, args);
    }
}
