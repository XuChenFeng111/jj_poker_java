package com.shengming.dao;

import com.shengming.entity.MallGoodsDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/2 9:37
 */
public interface MallGoodsMapper {

    List<MallGoodsDTO> findAll(@Param("category") Integer category);

    Integer findMinPrice();

    MallGoodsDTO findGoodsById(Integer goodsId);
}
