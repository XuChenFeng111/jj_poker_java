package com.shengming.service;

import com.shengming.entity.MailDTO;
import com.shengming.entity.MailDetailsDTO;

import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/2 15:27
 */
public interface MailService {

    int insert(MailDTO record);

    MailDTO ifTodaySend(Integer userid,Integer friendid);

    List<MailDetailsDTO> findMailFromFriendList(Integer userid);

    void updateStatus(Integer mailId);

    List<Integer> findIds(Integer userid);

    Integer findUserIdByMailId(Integer mailId);
}
