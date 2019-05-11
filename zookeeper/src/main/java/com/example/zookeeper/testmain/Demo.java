package com.example.zookeeper.testmain;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：mmzs
 * @date ：Created in 2019/4/2 0002 15:19
 * @description：zookeeper 测试启动类
 * @modified By：
 * @version: 1.0$
 */
public class Demo {
    /**
     *  ZK 连接地址
     */
    private static final  String CONNECTSTRING="127.0.0.1:2181";
    /**
     *  session time out
     */
    private static final int SESSIN_TIME_OUT=5000;

    //java 并法包 信号量技术 控制zk连接成功后 才开始创建节点
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(CONNECTSTRING, SESSIN_TIME_OUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 获取事件状态
                Watcher.Event.KeeperState keeperState = event.getState();
                // 获取事件类型
                Event.EventType eventType = event.getType();

                if (Event.KeeperState.SyncConnected == keeperState) {
                    if (Event.EventType.None == eventType) {
                        // 将计数器 -1
                        countDownLatch.countDown();
                        System.out.println("开启连接............");
                    }
                }
            }
        });
        //如果（信号量）计数器不为0 会一直等待
        countDownLatch.await();
        // 创建节点 持久化节点
        String result = zooKeeper.create("/testNode001", "testNode001".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println("result:" + result);
        Thread.sleep(1000 * 10);
        zooKeeper.close();
    }
}
