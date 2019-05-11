
package com.example.zookeeper.zklock.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：Hman
 * @date ：Created in 2019/4/3 0003 22:20
 * @description： 根据时间戳加计数 生成订单号
 * @modified By：
 * @version: 1.0
 */
public class OrderNumGenerator {
	// 生成订单号规则
	private static int count = 0;

	public String getNumber() {
		try {
			Thread.sleep(200);
		} catch (Exception e) {
		}
		SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		return simpt.format(new Date()) + "-" + ++count;
	}
}
