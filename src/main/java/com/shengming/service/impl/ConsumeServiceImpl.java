package com.shengming.service.impl;

import com.shengming.dao.ConsumeMapper;
import com.shengming.entity.ConsumeDTO;
import com.shengming.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/14 10:00
 */
@Service
public class ConsumeServiceImpl implements ConsumeService {
    @Autowired
    private ConsumeMapper consumeMapper;

    @Override
    public List<ConsumeDTO> findAll() {
        return consumeMapper.findAll();
    }

    @Override
    public ConsumeDTO findById(Integer convertid) {
        return consumeMapper.findById(convertid);
    }


}
