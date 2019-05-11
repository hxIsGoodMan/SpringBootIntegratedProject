package com.springcloud.ribbon.fallback;

import com.springcloud.ribbon.feignclient.FeignClientDemo;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/*
 * @Author Hman
 * @Description //TODO
 * @Date 2019/3/7 20:30
 * @Version  1.0
 **/
@Component
public class FeignFallback implements FeignClientDemo {

    @Override
    public String findAll() {
        System.out.println("降级处理类");
        return "feign 出错啦  豪猪出动~~";
    }

    @Override
    public String getById(String id) {

        System.out.println("getById 降级处理类");
        return "feign 出错啦  豪猪出动~~";
    }
}
