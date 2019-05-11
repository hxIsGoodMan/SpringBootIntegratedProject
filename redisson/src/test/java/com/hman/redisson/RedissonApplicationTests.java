package com.hman.redisson;



import com.hman.redisson.testdata.OrderService;
import com.hman.redisson.util.RedissonLockUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonApplicationTests {

    @Test
    public void RedissonLockTest() {
        System.out.println("多线程生成number-----------");
        for (int i = 0; i < 1; i++) {
            new Thread(new OrderService()).start();
        }
    }

    @Autowired
    private RedissonClient redisson;
    @Test
    public void redisson() {
//        redisson.getLock("123").lock();

//        String user_id="1";
//        String key=user_id+"_key";
//        //获取锁
//        RLock lock = RedissonLockUtil.lock(key);
//        lock.lock();

        //执行具体逻辑...

        RBucket<Integer> bucket = redisson.getBucket("count");
      //  bucket.set("bb");
        System.out.println(bucket.get().toString());
     //   lock.unlock();
    }
}
