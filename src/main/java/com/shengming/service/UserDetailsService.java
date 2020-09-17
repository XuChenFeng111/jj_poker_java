package com.shengming.service;

import com.shengming.entity.UserDetailsDTO;
import com.shengming.entity.UserIsrnDTO;

/**
 * @author XuChenFeng
 * @Date 2020/8/20 13:09
 */

public interface UserDetailsService {

    /**
     * 点击头像查看用户详情
    * */
    UserDetailsDTO getUserDetails(Integer userid);

    /**
     * 实名注册
     */
    void updateIsrn(Integer userId,String realname,String idCard,String token);

    /**
     * 更新推荐人
     * @param userid
     * @param recommenderId
     */
    boolean update_Recommender(Integer userid, Integer recommenderId);

    void updateNickname(Integer userId,String avatar,String nickName,Integer gender);

    //找出用户常玩游戏
    Integer findOftenGame(Integer userid);
}
