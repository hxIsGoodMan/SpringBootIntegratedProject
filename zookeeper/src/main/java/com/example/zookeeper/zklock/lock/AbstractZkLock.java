package com.example.zookeeper.zklock.lock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：Hman
 * @date ：Created in 2019/4/3 0003 21:54
 * @description：zk 分布式锁 锁方法 base方法
 * @modified By：
 * @version: 1.0$
 */
public abstract class AbstractZkLock {
    /**
     *  zk 连接地址
     */
    protected static final String ZK_ADDRESS="127.0.0.1:2181";

    /**
     *   zk客户端连接
     */
    protected ZkClient zkClient = new ZkClient(ZK_ADDRESS);

    /**
     *  信号量对象 用于阻塞线程
      */
    protected CountDownLatch countDownLatch = new CountDownLatch(1);

    public Boolean getLock(){
        if (tryLock()) {
            System.out.println("####获取锁成功######");
            return true;
        } else {
            waitLock();
            getLock();
        }

        return false;
    }

    protected abstract void waitLock();

    protected abstract boolean tryLock();

    public void unLock(){
        if(zkClient!=null){
            zkClient.close();
            System.out.println("##########解锁成功###########");
        }
    }

}
