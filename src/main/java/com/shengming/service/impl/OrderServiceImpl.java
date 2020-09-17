package com.shengming.service.impl;

import com.shengming.dao.OrderMapper;
import com.shengming.entity.OrderDTO;
import com.shengming.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author XuChenFeng
 * @Date 2020/9/2 13:20
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int insert(OrderDTO record) {
        int insert = orderMapper.insert(record);
        return insert;
    }
}
