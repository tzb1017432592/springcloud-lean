package com.example.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ServiceInfoUtil serviceInfoUtil;

    @GetMapping("/client01/hello")
    @Profile(value = {"cl01","clo2"})
    public String test(){
        List<String> services = discoveryClient.getServices();
        return "服务消费："+services+",端口号："+serviceInfoUtil.getPort();
    }

    @GetMapping("/consumer/hello")
    @Profile(value = {"cl03"})
    public String consumer(){
        return restTemplate.getForEntity("http://EUREKA-CLIENT01/client01/hello",String.class).getBody();
    }
}
