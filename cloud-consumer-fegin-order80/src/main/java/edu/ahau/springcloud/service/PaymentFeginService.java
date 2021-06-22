package edu.ahau.springcloud.service;

import edu.ahau.springcloud.entities.CommonResult;
import edu.ahau.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: zhangxuna
 * @create: 2021-06-22 20:02
 * @description: TODO
 */
@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeginService {
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    // 注解必须保持一样，要不然会报错404
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();

}
