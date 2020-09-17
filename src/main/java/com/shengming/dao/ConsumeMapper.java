package com.shengming.dao;

import com.shengming.entity.ConsumeDTO;

import java.util.List;

public interface ConsumeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConsumeDTO record);

    int insertSelective(ConsumeDTO record);
    //查询所有
    List<ConsumeDTO> findAll();
    //根据id查询
    ConsumeDTO findById(Integer convertid);

    int updateByPrimaryKeySelective(ConsumeDTO record);

    int updateByPrimaryKey(ConsumeDTO record);
}