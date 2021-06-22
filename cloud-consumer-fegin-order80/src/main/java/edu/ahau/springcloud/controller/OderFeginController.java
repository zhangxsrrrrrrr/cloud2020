package edu.ahau.springcloud.controller;

import edu.ahau.springcloud.entities.CommonResult;
import edu.ahau.springcloud.entities.Payment;
import edu.ahau.springcloud.service.PaymentFeginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangxuna
 * @create: 2021-06-22 20:08
 * @description: TODO
 */
@RestController
@Slf4j
public class OderFeginController {

    @Autowired
    private PaymentFeginService paymentFeginService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeginService.getPaymentById(id);
    }
    @GetMapping(value = "/consumer/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentFeginService.paymentFeignTimeout();
    }
}
