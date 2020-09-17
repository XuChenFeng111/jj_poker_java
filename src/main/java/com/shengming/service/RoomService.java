package com.shengming.service;

import com.shengming.entity.RoomDTO;

import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/8 16:56
 */
public interface RoomService {

    List<RoomDTO> findAll(Integer gametype);

    void insert(RoomDTO record);

    List<String> findPlayerNum(Integer gametype);

    RoomDTO selectByPrimaryKey(Integer id);

    Integer findNumById(Integer id);

    void updateNumById(Integer id,String playernum);

    String  findPlayNumById(Integer id);

    void deleteByPlayNum(Integer gametype, String playernum);

    String findUserIds(String userid);
}
