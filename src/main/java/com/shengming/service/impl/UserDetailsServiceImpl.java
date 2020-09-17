package com.shengming.service.impl;

import com.shengming.dao.UserMapper;
import com.shengming.entity.UserIsrnDTO;
import com.shengming.entity.UserDetailsDTO;
import com.shengming.service.UserDetailsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author XuChenFeng
 * @Date 2020/8/20 13:10
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询用户详细信息
     */
    @Override
    public UserDetailsDTO getUserDetails(Integer userid) {
        return userMapper.selectByPrimaryKey(userid);
    }

    /**
     * 实名注册
     */
    @Override
    public void updateIsrn(Integer userId,String realname,String idCard,String token) {
        userMapper.updateIsrn(userId,realname,idCard,token);
    }

    @Override
    public boolean update_Recommender(Integer userid, Integer recommender_id) {
        return userMapper.update_Recommender(userid, recommender_id);
    }

    @Override
    public void updateNickname(Integer userId, String avatar, String nickName,Integer gender) {
        String ifNickname = userMapper.ifNickname(nickName);
        //查看是否有重复昵称
        if (ifNickname == null) {
            userMapper.updateNickname(userId, avatar, nickName,gender);
        }
    }

    @Override
    public Integer findOftenGame(Integer userid) {
        return userMapper.findOftenGame(userid);
    }


}
