package com.hman.redisson.testdata;

import com.hman.redisson.util.RedissonLockUtil;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

/**
 * @author ：Hman
 * @date ：Created in 2019/4/3 0003 22:40
 * @description： 模拟多线程同时创建订单号
 * @modified By：
 * @version: 1.0
 */
@Component
public class OrderService implements Runnable {
	private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();

	private static final String LOCK_NAME="REDIS_LOCK";
	private static final Integer LOCK_TIME_OUT=2;


	@Override
	public void run() {
		getNumber();
	}

	public void getNumber() {
		RLock rLock=null;
		try {
			rLock= RedissonLockUtil.lock(LOCK_NAME);
			rLock.lock();
			String number = orderNumGenerator.getNumber();
			System.out.println("线程:" + Thread.currentThread().getName() + ",生成订单id:" + number);
		} catch (Exception e) {
			System.err.println("---报错啦---");
			e.printStackTrace();
		} finally {
			RedissonLockUtil.unlock(rLock);
		}
	}

}
