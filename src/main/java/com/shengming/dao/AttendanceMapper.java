package com.shengming.dao;

import com.shengming.entity.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AttendanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("userId") Integer userId,@Param("signtime")  Date signtime,@Param("continuousdays") Integer continuousdays,@Param("prize") String prize);

    int insertSelective(Attendance record);

    Attendance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKey(Attendance record);

    List<Attendance> list(Integer memberFkUserId);

    List<Attendance> listfordate(@Param("memberFkUserId") Integer memberFkUserId,@Param("monday") String monday,@Param("sunday") String sunday);
}