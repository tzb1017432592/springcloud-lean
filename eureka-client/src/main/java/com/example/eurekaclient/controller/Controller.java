package com.example.eurekaclient.controller;

import com.example.eurekaclient.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/client01")
public class Controller {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ServiceInfoUtil serviceInfoUtil;

    @Autowired
    private TestService testService;

    @GetMapping("/feign/test1")
    public String test1() throws InterruptedException {
        return testService.test1();
    }
    @GetMapping("/feign/test2")
    public String test2(Integer num) throws InterruptedException {
        Thread.sleep(num);
        return "超时测时";
    }
    @GetMapping("/feign/test3")
    public String test3(String message) throws InterruptedException {
        throw new RuntimeException(message);
    }

    @GetMapping("/hello")
    public String test(){
        List<String> services = discoveryClient.getServices();
        return "服务消费："+services+",端口号："+serviceInfoUtil.getPort();
    }

    @GetMapping("/hystrix/{num}")
    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "500"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
            })
    public String test4(@PathVariable("num") Integer num) throws InterruptedException {
        Thread.sleep(num);
        List<String> services = discoveryClient.getServices();
        return "服务消费："+services+",端口号："+serviceInfoUtil.getPort();
    }

    public String fallbackMethod(Integer num){
        return "超时时间："+num+" 发生降级";
    }
}
