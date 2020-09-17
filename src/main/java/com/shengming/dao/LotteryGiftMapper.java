package com.shengming.dao;

import com.shengming.entity.LotteryGiftDTO;

import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/3 13:33
 */

public interface LotteryGiftMapper {

    List<LotteryGiftDTO> findAllList();

    Double findProbability(String gitfId);

    LotteryGiftDTO findGiftNameById(Integer id);
}
