package edu.ahau.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import edu.ahau.springcloud.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zhangxuna
 * @create: 2021-06-24 8:55
 * @description: 使用全局默认异常的时候需要注意返回值的通用性
 */
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OderHystrixOderController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;


    private static final String HYSTRIX_URL = "http://CLOUD-PROVIDER-HYSTRIX-PAYMENT";
    @GetMapping("/getok/{id}")
    public String paymentInfo_OK(@PathVariable("id") int id){
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/getfail/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_FAILHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")
    })
    public String paymentInfo_FAIL(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_FAIL(id);
    }

    public String paymentInfo_FAILHandler(Integer id) {

        return "线程池：" + Thread.currentThread().getName() + "客户端paymentInfo_FAILHandler,id: " + id + "\t" + "就啊哈O(∩_∩)O";
    }

    // 下面是全局fallback
    public String payment_Global_FallbackMethod(){
        return "这是默认的全局异常，(●'◡'●)";
    }
}
