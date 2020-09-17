package com.shengming.service.impl;

import com.shengming.dao.ActivitylistMapper;
import com.shengming.entity.Activitylist;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author donggege
 * @create 2020-08-27 13:30
 */
public class ActivitylistImpl implements ActivitylistMapper {

    @Resource
    private ActivitylistMapper activitylistMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Activitylist record) {
        return 0;
    }

    @Override
    public int insertSelective(Activitylist record) {
        return 0;
    }

    @Override
    public Activitylist selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Activitylist record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Activitylist record) {
        return 0;
    }

    @Override
    public List<Activitylist> select_all() {
        return activitylistMapper.select_all();
    }
}
