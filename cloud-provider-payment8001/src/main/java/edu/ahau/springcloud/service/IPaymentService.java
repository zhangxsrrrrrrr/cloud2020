package edu.ahau.springcloud.service;

import edu.ahau.springcloud.entities.Payment;

/**
 * @author: zhangxuna
 * @create: 2021-05-18 14:09
 * @description: TODO
 */
public interface IPaymentService {
    int create(Payment payment);

    Payment getPaymentById(long id);
}
