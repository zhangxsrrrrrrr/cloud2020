package edu.ahau.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zhangxuna
 * @create: 2021-06-22 20:00
 * @description: TODO
 */
@SpringBootApplication
@EnableFeignClients
public class OderFeginMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OderFeginMain80.class, args);
    }
}
