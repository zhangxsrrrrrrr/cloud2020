package edu.ahau.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author: zhangxuna
 * @create: 2021-06-24 22:41
 * @description: TODO
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_FAIL(Integer id) {
        return "----PaymentFallbackService  fall back paymentInfo_FAIL,/(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_OK(int id) {
        return "----PaymentFallbackService  fall back paymentInfo_OK,/(ㄒoㄒ)/~~";
    }
}
