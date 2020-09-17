package com.shengming.service.impl;

import com.shengming.dao.AttendanceMapper;
import com.shengming.dao.UserMapper;
import com.shengming.entity.Attendance;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class AttendanceServiceImpl implements AttendanceMapper {

    @Resource
    private AttendanceMapper attendanceMapper;



    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Integer userId, Date signtime, Integer continuousdays, String prize) {
        return attendanceMapper.insert(userId,signtime,continuousdays,prize);
    }

    @Override
    public int insertSelective(Attendance record) {
        return 0;
    }

    @Override
    public Attendance selectByPrimaryKey(Integer id) {
        return attendanceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Attendance record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Attendance record) {
        return 0;
    }

    @Override
    public List<Attendance> list(Integer memberFkUserId) {
        return attendanceMapper.list(memberFkUserId);
    }

    @Override
    public List<Attendance> listfordate(Integer memberFkUserId, String monday, String sunday) {
        return attendanceMapper.listfordate(memberFkUserId,monday,sunday);
    }
}
