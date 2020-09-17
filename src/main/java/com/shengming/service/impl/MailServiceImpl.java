package com.shengming.service.impl;

import com.shengming.dao.MailMapper;
import com.shengming.entity.MailDTO;
import com.shengming.entity.MailDetailsDTO;
import com.shengming.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/2 15:27
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private MailMapper mailMapper;

    @Override
    public int insert(MailDTO record) {
        int insert = mailMapper.insert(record);
        return insert;
    }

    @Override
    public MailDTO ifTodaySend(Integer userid, Integer friendid) {
        return mailMapper.ifTodaySend(userid,friendid);
    }

    @Override
    public List<MailDetailsDTO> findMailFromFriendList(Integer userid) {
        return mailMapper.findMailFromFriendList(userid);
    }

    @Override
    public void updateStatus(Integer mailId) {
        mailMapper.updateStatus(mailId);
    }

    @Override
    public List<Integer> findIds(Integer userid) {
        return mailMapper.findIds(userid);
    }

    @Override
    public Integer findUserIdByMailId(Integer mailId) {
        return mailMapper.findUserIdByMailId(mailId);
    }


}
