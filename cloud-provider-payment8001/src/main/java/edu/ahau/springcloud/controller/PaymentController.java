package edu.ahau.springcloud.controller;


import edu.ahau.springcloud.dao.PaymentDao;
import edu.ahau.springcloud.entities.CommonResult;
import edu.ahau.springcloud.entities.Payment;
import edu.ahau.springcloud.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: zhangxuna
 */

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Resource
    private final IPaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private PaymentDao paymentDao;

    @Value("${server.port}")
    private  String serverPort;

    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;

    }

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*******插入结果："+result);

        if(result > 0)
        {
            return new CommonResult(200,"插入数据库成功,serverPort: "+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败,serverPort: "+serverPort,null);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null)
        {
            return new CommonResult(200,"查询成功,serverPort: "+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: "+id,null);
        }
    }



    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout()
    {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }

    @GetMapping("/discover")
    public Object discover(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(String.valueOf(instance.getPort())+instance.getServiceId()+instance.getUri());
        }
        return this.discoveryClient;
    }

    // TODO: 2021/6/22 测试
    @PostMapping("/get/{name}")
    public String get(@RequestBody String id,@PathVariable("name") String name){
        return id + "   " + name;
    }
}
