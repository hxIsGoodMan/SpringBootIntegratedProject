package com.hman.redisson;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@SpringBootApplication
public class RedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedissonApplication.class, args);
    }

    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson() throws IOException {
        RedissonClient redisson = Redisson.create(
                Config.fromYAML(new ClassPathResource("redisson.yml").getInputStream()));
        return redisson;
    }

}
