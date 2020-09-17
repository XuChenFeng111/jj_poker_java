package com.shengming.dao;

import com.shengming.entity.OrderDTO;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDTO record);

    int insertSelective(OrderDTO record);

    OrderDTO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDTO record);

    int updateByPrimaryKey(OrderDTO record);
}