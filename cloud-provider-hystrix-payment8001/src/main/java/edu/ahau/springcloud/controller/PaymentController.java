package edu.ahau.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import edu.ahau.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.PrivateKey;

/**
 * @author: zhangxuna
 * @create: 2021-06-23 21:04
 * @description: TODO
 */
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/getok/{id}")
    public String paymentInfo_OK(@PathVariable("id") int id){
        return paymentService.paymentInfo_OK(id);
    }

    @GetMapping("/getfail/{id}")
    public String paymentInfo_FAIL(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_FAIL(id);
    }

}
