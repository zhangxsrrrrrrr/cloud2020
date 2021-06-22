package edu.ahau.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhangxuna
 * @create: 2021-06-21 21:53
 * @description: TODO
 */
@Component
public class MyLB implements LoadBalancer{

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            // 防止越界
            next = current >= Integer.MAX_VALUE ? 0 : current+1;

        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("第"+next+"次访问");
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
