package com.example.zookeeper.zklock.lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：Hman
 * @date ：Created in 2019/4/3 0003 22:10
 * @description：zk 分布式锁 实现类 用于实现具体 分布式锁的方法
 * @modified By：
 * @version: 1.0$
 */
public class DistrbuteZkLock extends AbstractZkLock {

    public final String lockNodeName="/lock";

    /**
     * create by: Hman
     * description:  阻塞线程 ， 监听zk node事件
     * create time: 2019/4/3 0003 22:11
     * @return
     */
    @Override
    public void waitLock() {

        // 使用zk临时事件监听
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataDeleted(String path) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
            @Override
            public void handleDataChange(String arg0, Object arg1) throws Exception {

            }
        };
        // 注册事件通知
        zkClient.subscribeDataChanges(lockNodeName, iZkDataListener);
        if (zkClient.exists(lockNodeName)) {
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 监听完毕后，移除事件通知 (避免重复绑定事件，影响性能)
        zkClient.unsubscribeDataChanges(lockNodeName, iZkDataListener);

    }
    /**
     * create by: Hman
     * description:  尝试创建lock临时节点 若不行 则返回false
     * create time: 2019/4/3 0003 22:11
     * @return  是否创建成功
     */
    @Override
    public boolean tryLock() {
        try {
            zkClient.createEphemeral(lockNodeName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
