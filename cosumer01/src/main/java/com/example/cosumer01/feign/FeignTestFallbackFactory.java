package com.example.cosumer01.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FeignTestFallbackFactory implements FallbackFactory<FeignTest> {
    @Override
    public FeignTest create(Throwable throwable) {
        if (throwable != null) {
            log.error("fallback reason was:" + throwable.getMessage(), throwable);
        }
        log.error("发生错误【{}】",throwable.toString());
        return new FeignTest() {
            @Override
            public String test1() {
                log.error("降级容错");
                return "test1发生降级容错啦";
            }

            @Override
            public String test2(Integer num) {
                log.error("降级容错");
                return "test2发生降级容错啦";
            }

            @Override
            public String test3(String message) {
                log.error("降级容错");
                return "test3发生降级容错啦";
            }

            @Override
            public String test4(Integer message) {
                log.error("降级容错");
                return "test4发生降级:"+message;
            }
        };
    }
}
