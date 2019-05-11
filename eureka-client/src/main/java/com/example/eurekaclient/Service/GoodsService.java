package com.example.eurekaclient.Service;

import com.example.eurekaclient.pojo.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:H-man
 * @Date:${Date}${Time}
 * @Description : 商品类 Service 接口
 * @Version: 1.0
 */
public interface GoodsService {

    Goods getById(String id);

    List<Goods> findAll();
}
