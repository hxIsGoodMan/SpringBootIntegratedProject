package com.example.zookeeper.zklock.lock;

/**
 * @author ：Hman
 * @date ：Created in 2019/4/3 0003 22:40
 * @description： 模拟多线程同时创建订单号
 * @modified By：
 * @version: 1.0
 */
public class OrderService implements Runnable {
	private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
	private AbstractZkLock zkLock = new DistrbuteZkLock();

	@Override
	public void run() {
		getNumber();
	}

	public void getNumber() {
		try {
			zkLock.getLock();
			String number = orderNumGenerator.getNumber();
			System.out.println("线程:" + Thread.currentThread().getName() + ",生成订单id:" + number);
		} catch (Exception e) {

		} finally {
			zkLock.unLock();
		}
	}

	public static void main(String[] args) {
		System.out.println("多线程生成number-----------");

		for (int i = 0; i < 100; i++) {
			new Thread(new OrderService()).start();
		}
	}

}
