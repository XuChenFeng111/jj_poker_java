package com.shengming.service.impl;

import com.shengming.dao.RoomMapper;
import com.shengming.entity.RoomDTO;
import com.shengming.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/8 16:56
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<RoomDTO> findAll(Integer gametype) {
        return roomMapper.findAll(gametype);
    }

    @Override
    public void insert(RoomDTO record) {
        roomMapper.insert(record);
    }

    @Override
    public List<String> findPlayerNum(Integer gametype) {
        return roomMapper.findPlayerNum(gametype);
    }

    @Override
    public RoomDTO selectByPrimaryKey(Integer id) {
        return roomMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer findNumById(Integer id) {
        return roomMapper.findNumById(id);
    }

    @Override
    public void updateNumById(Integer id,String playernum) {
        roomMapper.updateNumById(id,playernum);
    }

    @Override
    public String findPlayNumById(Integer id) {
        return roomMapper.findPlayNumById(id);
    }

    @Override
    public void deleteByPlayNum(Integer gametype, String playernum) {
        roomMapper.deleteByPlayNum(gametype,playernum);
    }

    @Override
    public String findUserIds(String userid) {
        return roomMapper.findUserIds(userid);
    }
}
