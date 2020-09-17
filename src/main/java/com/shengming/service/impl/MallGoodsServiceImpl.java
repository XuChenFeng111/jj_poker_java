package com.shengming.service.impl;

import com.shengming.dao.MallGoodsMapper;
import com.shengming.entity.MallGoodsDTO;
import com.shengming.service.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/2 9:44
 */
@Service
public class MallGoodsServiceImpl implements MallGoodsService {
    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    @Override
    public List<MallGoodsDTO> findAll(Integer category) {
        return mallGoodsMapper.findAll(category);
    }

    @Override
    public Integer findMinPrice() {
        return mallGoodsMapper.findMinPrice();
    }

    @Override
    public MallGoodsDTO findGoodsById(Integer goodsId) {
        return mallGoodsMapper.findGoodsById(goodsId);
    }


}
