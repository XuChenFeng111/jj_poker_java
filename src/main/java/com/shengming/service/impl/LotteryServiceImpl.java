package com.shengming.service.impl;

import com.shengming.dao.LotteryMapper;
import com.shengming.entity.LotteryGiftDTO;
import com.shengming.entity.UserDTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author donggege
 * @create 2020-08-27 10:47
 */
public class LotteryServiceImpl implements LotteryMapper {

    @Resource
    private LotteryMapper lotteryMapper;

    @Override
    public boolean update_lottery(Integer userid, Integer point, Integer goldcoin) {
        return false;
    }


}
