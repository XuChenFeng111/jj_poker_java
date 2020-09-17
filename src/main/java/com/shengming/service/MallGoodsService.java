package com.shengming.service;

import com.shengming.entity.MallGoodsDTO;

import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/2 9:44
 */
public interface MallGoodsService {
    List<MallGoodsDTO> findAll(Integer category);

    Integer findMinPrice();

    MallGoodsDTO findGoodsById(Integer goodsId);
}
