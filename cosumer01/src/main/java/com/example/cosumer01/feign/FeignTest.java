package com.example.cosumer01.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-client01",fallbackFactory = FeignTestFallbackFactory.class)
public interface FeignTest {

    @GetMapping("/client01/feign/test1")
    String test1();

    @GetMapping("/client01/feign/test2")
    String test2(@RequestParam("num") Integer num);

    @GetMapping("/client01/feign/test3")
    String test3(@RequestParam("message") String message);

    @GetMapping("/client01/hystrix/{num}")
    String test4(@PathVariable("num") Integer message);
}
