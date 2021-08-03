package com.example.cosumer01.controller;

import com.example.cosumer01.feign.FeignTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class TestController {
    @Autowired
    private FeignTest feignTest;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String consumer(){
        return "rest:"+restTemplate.getForEntity("http://EUREKA-CLIENT01/client01/hello",String.class).getBody();
    }

    @GetMapping("/feign1")
    public String feignTest1(){
        return "feign1:"+feignTest.test1();
    }

    @GetMapping("/feign2/{num}")
    public String feignTest1(@PathVariable("num") Integer num){
        return "feign2:"+feignTest.test2(num);
    }

    @GetMapping("/feign3/{message}")
    public String feignTest1(@PathVariable("message") String message){
        return "feign3:"+feignTest.test3(message);
    }

    @GetMapping("/feign4/{num}")
    public String feignTest4(@PathVariable("num") Integer num){
        return "feign4:"+feignTest.test4(num);
    }
}
