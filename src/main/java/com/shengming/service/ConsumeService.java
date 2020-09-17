package com.shengming.service;

import com.shengming.entity.ConsumeDTO;

import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/14 10:00
 */
public interface ConsumeService {
    List<ConsumeDTO> findAll();

    ConsumeDTO findById(Integer convertid);
}
