package edu.ahau.springcloud.service;

/**
 * @author: zhangxuna
 * @create: 2021-06-23 20:58
 * @description: TODO
 */

import ch.qos.logback.core.util.TimeUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "PaymentInfo_OK,id: " + id + "\t" + "就啊哈";
    }

    // 划定出错行为界限
    @HystrixCommand(fallbackMethod = "paymentInfo_FAILHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "9000")
    })
    public String paymentInfo_FAIL(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "PaymentInfo_FAIL,id: " + id + "\t" + "就啊哈";
    }

    public String paymentInfo_FAILHandler(Integer id) {

        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_FAILHandler,id: " + id + "\t" + "就啊哈O(∩_∩)O";
    }
}
