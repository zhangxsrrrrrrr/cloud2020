package edu.ahau.springcloud.service.impl;

import edu.ahau.springcloud.dao.PaymentDao;
import edu.ahau.springcloud.entities.Payment;
import edu.ahau.springcloud.service.IPaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangxuna
 * @create: 2021-05-18 14:09
 * @description: TODO
 */
@Service
public class PaymentServiceImpl implements IPaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.save(payment);
    }

    @Override
    public Payment getPaymentById(long id) {
        return paymentDao.selectPay(id);
    }
}
