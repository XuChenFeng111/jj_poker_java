package com.shengming.dao;

import com.shengming.entity.MailDTO;
import com.shengming.entity.MailDetailsDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MailDTO record);

    MailDTO selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(MailDTO record);

    MailDTO ifTodaySend(@Param("userid") Integer userid,@Param("friendid")  Integer friendid);

    List<MailDetailsDTO> findMailFromFriendList(Integer userid);

    //根据mailId更改邮件状态
    void updateStatus(Integer mailId);

    List<Integer> findIds(Integer userid);

    Integer findUserIdByMailId(Integer mailId);
}