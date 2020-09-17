package com.shengming.dao;

import com.shengming.entity.Activitylist;

import java.util.List;

public interface ActivitylistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activitylist record);

    int insertSelective(Activitylist record);

    Activitylist selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activitylist record);

    int updateByPrimaryKey(Activitylist record);

    List<Activitylist> select_all();
}