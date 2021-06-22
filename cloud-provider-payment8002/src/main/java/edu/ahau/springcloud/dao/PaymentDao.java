package edu.ahau.springcloud.dao;

import edu.ahau.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: zhangxuna
 * @create: 2021-05-18 13:43
 */
@Mapper
public interface PaymentDao {
    public int save(@Param("payment") Payment payment);

    public Payment selectPay(@Param("id") Long id);
}
