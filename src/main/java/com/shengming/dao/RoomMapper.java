package com.shengming.dao;

import com.shengming.entity.RoomDTO;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import javax.swing.*;
import java.util.List;

public interface RoomMapper {
    int deleteByPrimaryKey(Integer id);

    void insert(RoomDTO record);

    int insertSelective(RoomDTO record);

    RoomDTO selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(RoomDTO record);

    int updateByPrimaryKey(RoomDTO record);

    List<RoomDTO> findAll(@Param("gametype") Integer gametype);

    List<String> findPlayerNum(Integer gametype);

    Integer findNumById(Integer id);

    void updateNumById(@Param("id") Integer id, @Param("playernum") String playernum);

    String findPlayNumById(Integer id);


    void deleteByPlayNum(@Param("gametype") Integer gametype,@Param("playernum") String playernum);

    String findUserIds(String userid);
}