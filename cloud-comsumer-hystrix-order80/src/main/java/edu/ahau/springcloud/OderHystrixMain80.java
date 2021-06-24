package edu.ahau.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zhangxuna
 * @create: 2021-06-24 8:52
 * @description: TODO
 */
@SpringBootApplication
@EnableHystrix
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class OderHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OderHystrixMain80.class, args);
    }
}
