package edu.ahau.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: zhangxuna
 * @create: 2021-06-24 12:40
 * @description: TODO
 */
@Service
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping("/getfail/{id}")
    public String paymentInfo_FAIL(@PathVariable("id") Integer id);

    @GetMapping("/getok/{id}")
    public String paymentInfo_OK(@PathVariable("id") int id);
}
