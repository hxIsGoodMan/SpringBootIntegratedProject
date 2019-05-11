package com.springcloud.ribbon.service;

import com.springcloud.ribbon.domain.Order;

/**
 * @Author:H-man
 * @Date:${Date}${Time}
 * @Description : 模拟订单类
 * @Version: 1.0
 */
public interface OrderService {

    /**
     *  模拟添加订单方法
     * @param goodsId  商品ID
     * @param num 购买数量
     * @return 返回 订单实体
     */
    Order add(String goodsId, int num);
}
