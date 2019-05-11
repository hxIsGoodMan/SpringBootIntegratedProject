package com.springcloud.ribbon.feignclient;

import com.springcloud.ribbon.fallback.FeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:H-man
 * @Date:${Date}${Time}
 * @Description : 类的作用
 * @Version: 1.0
 */

@FeignClient(name = "goods",fallback = FeignFallback.class)
public interface FeignClientDemo {

    @RequestMapping(value = "/goods/findAll",method = RequestMethod.GET)
    String findAll();

    @RequestMapping(value = "/goods/getById/{id}",method = RequestMethod.GET)
    String getById(@PathVariable("id") String id);
}
