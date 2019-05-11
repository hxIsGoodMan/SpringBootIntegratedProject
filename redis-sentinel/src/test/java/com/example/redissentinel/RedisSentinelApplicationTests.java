package com.example.redissentinel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisSentinelApplicationTests {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void write() {
        redisTemplate.opsForValue().set("testForMe","测试哨兵连接-master关闭后");

    }

    @Test
    public void read() {
        System.out.println(redisTemplate.opsForValue().get("testForMe"));
    }
    @Test
    public void writeAndRead() {
        redisTemplate.opsForValue().set("testForMe","测试哨兵连接-master关闭后");
        System.out.println(redisTemplate.opsForValue().get("testForMe"));
    }

}
