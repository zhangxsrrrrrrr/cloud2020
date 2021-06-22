package edu.ahau.springcloud.controller;

import edu.ahau.springcloud.entities.CommonResult;
import edu.ahau.springcloud.entities.Payment;
import edu.ahau.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author: zhangxuna
 * @create: 2021-05-18 22:00
 * @description: TODO
 */

@Slf4j
@RestController
public class OrderController {
//    public static final String PAYMENT_URL="http://localhost:8001";
    // 填写eureka服务上的名称
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancer loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> createPayment(Payment payment) {
        // 指定访问的客户端url，传入到指定客户段的信息，传入的类型
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        // 指定访问的客户端url，获取到类型
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPaymentForEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }else {
            return new CommonResult(444,"操作失败");
        }
    }
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances==null||instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

    @PostMapping(value = "/consumer/get/{id}")
    public String get1(@PathVariable("id") String id) {
        HttpEntity<String> httpEntity = new HttpEntity<>(id);
        return restTemplate.postForObject(PAYMENT_URL+"/payment/get/{name}",httpEntity,String.class,"zhangsan");
    }

    @PostMapping(value = "/consumer/do/{id}")
    public String get2(@PathVariable("id") String id) {
        HttpEntity<String> httpEntity = new HttpEntity<>(id);
        ResponseEntity<String> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/get/{name}", httpEntity, String.class, "zhangsan");
        String body = (String) entity.getBody();
        System.out.println(entity.getStatusCode());
        System.out.println(entity.getHeaders());
        System.out.println(entity.getStatusCodeValue());
        return body;
    }
}
