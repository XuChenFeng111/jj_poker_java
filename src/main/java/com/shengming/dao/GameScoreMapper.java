package com.shengming.dao;

import com.shengming.entity.GameScoreDTO;

public interface GameScoreMapper {
    int deleteByPrimaryKey(Integer id);

    void insert(GameScoreDTO record);

    int insertSelective(GameScoreDTO record);

    GameScoreDTO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameScoreDTO record);

    int updateByPrimaryKey(GameScoreDTO record);
}