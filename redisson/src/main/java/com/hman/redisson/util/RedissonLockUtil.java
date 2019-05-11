package com.hman.redisson.util;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ：Hman
 * @date ：Created in 2019/4/4 0004 13:08
 * @description：RedissLockUtil 工具类
 * @modified By：
 * @version: 1.0
 */
@Component
public class RedissonLockUtil {


    private static RedissonClient redissonClient;

    /**
     *  RedissLockUtil 构造器
     *      因为@AutoWired注解没办法注入静态成员变量 于是使用构造器注入的方式
     * @param redissonClient RedissonClient 给予Spring 自动注入
     */
    @Autowired
    public RedissonLockUtil(RedissonClient redissonClient) {
        RedissonLockUtil.redissonClient = redissonClient;
    }

    public RedissonLockUtil(){}
    /**
     * 获取锁 若锁被占有 会一直获取 直到获取到 为止
     *
     * @param lockKey
     * @return
     */
    public static RLock lock(String lockKey) {
        RLock lock=redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }
    /**
     * 获取锁 并设置过期时间 单位：秒
     *
     * @param lockKey
     * @param timeOut 过期时间 单位秒
     * @return
     */
    public static RLock lock(String lockKey,long timeOut) {
        RLock lock=redissonClient.getLock(lockKey);
        lock.lock(timeOut,TimeUnit.SECONDS);
        return lock;
    }

    /**
     *  获取锁 并设置 过期时间 设置 单位
     * @param lockKey 锁名
     * @param timeOut 过期时间
     * @param unit  过期时间单位
     * @return 锁对象
     */
    public static RLock lock(String lockKey,long timeOut,TimeUnit unit) {
        RLock lock=redissonClient.getLock(lockKey);
        lock.lock(timeOut,unit);
        return lock;
    }

    /**
     * 尝试获取锁，获取到立即返回 锁对象,获取失败立即返回f null
     * @param lockKey
     * @return 锁对象
     */
    public static RLock tryLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        if(lock.tryLock()){
            return lock;
        }else{
            return null;
        }
    }

    /**
     * 尝试获取锁，在给定的waitTime时间内尝试，获取到返回 锁对象,获取失败返回null,获取到后再给定的leaseTime时间超时释放
     * @param lockKey
     * @param waitTime  获取等待时间
     * @param leaseTime
     * @param unit
     * @return 锁对象
     * @throws InterruptedException
     */
    public static RLock tryLock(String lockKey, long waitTime, long leaseTime,
                           TimeUnit unit) throws InterruptedException{
        RLock lock = redissonClient.getLock(lockKey);
        if(lock.tryLock(waitTime, leaseTime, unit)){
            return lock;
        }else{
            return null;
        }
    }

    /**
     * 锁释放被任意一个线程持有
     * @param lockKey
     * @return
     */
    public static boolean isLocked(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.isLocked();
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    public static void unlock(RLock lock) {
        if (lock != null) {
            lock.unlock();

        }
    }

}
