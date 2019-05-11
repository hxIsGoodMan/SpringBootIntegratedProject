package com.hman.redisson.controller;

import com.hman.redisson.util.RedissonLockUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author ：Hman
 * @date ：Created in 2019/4/4 0004 13:42
 * @description：模拟商品购买
 * @modified By：
 * @version: 1.0
 */
@RestController
public class GoodsController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("buy")
    public String buy(){
        String tips="";
        RLock rLock=null;
        try{
          // rLock= RedissonLockUtil.tryLock("redisson_lock",3L,5L, TimeUnit.SECONDS);
            if(true){
                //抢到锁了 从redis中获取count 并且-1
                RBucket<Integer> bucket = redissonClient.getBucket("count");
                int count=bucket.get();
                if(count>0){
                    bucket.set(--count);
                    tips="购买成功了亲，还剩库存:"+count;
                }else{
                    tips="商品售空，活动已结束~~~ ";
                }

            }else{
                //未抢到锁
                tips="人太多了！！！，请稍后再试";

            }
        }catch (Exception e){
            e.printStackTrace();
            tips="人太多了！！！，请稍后再试";
        }finally {
           // RedissonLockUtil.unlock(rLock);
        }
        System.out.println(tips);
        return tips;
    }
}
