package com.shengming.service.impl;

import com.shengming.dao.GameScoreMapper;
import com.shengming.entity.GameScoreDTO;
import com.shengming.service.GameScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author XuChenFeng
 * @Date 2020/9/7 15:48
 */
@Service
public class GameScoreServiceImlp implements GameScoreService {

    @Autowired
    private GameScoreMapper gameScoreMapper;

    @Override
    public void insert(GameScoreDTO record) {
        gameScoreMapper.insert(record);
    }
}
