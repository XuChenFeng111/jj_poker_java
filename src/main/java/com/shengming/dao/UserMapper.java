package com.shengming.dao;

import com.shengming.entity.UserDTO;
import com.shengming.entity.UserDetailsDTO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);
    //    插入用户信息
    int insert(UserDTO record);

    int insertSelective(UserDTO record);
    //查询用户详细信息
    UserDetailsDTO selectByPrimaryKey(Integer userid);
    //统计推荐人数量
    int findRecomNum(Integer userid);

    int updateByPrimaryKeySelective(UserDTO record);

    int updateByPrimaryKeyWithBLOBs(UserDTO record);

    int updateByPrimaryKey(UserDTO record);
    //实名注册
    void updateIsrn(@Param("userId") Integer userId,@Param("realname")  String realname,@Param("idCard") String idCard,@Param("token") String token);

    boolean update_Recommender(Integer userid,Integer recommenderId);
    //查询手机号是否存在
    String ifPho (String mobile);
    //查询该手机号对应的openid是否存在 确定是手机号首次登陆
    String ifOpenidByMobile(String mobile);
    //查询是否有重复的昵称
    String ifNickname(String nickname);
    //根据手机号码查询token
    String getToken(String userid);
    //根据手机号码更新token
    void updateToken(@Param("token") String token,@Param("mobile") String mobile);
    //查询是否有openid
    String ifOpenId(String openId);
    //根据openid更新token
    void updateTokenForVX(@Param("token") String token,@Param("openId") String openId);
    //主页更换头像和昵称和性别
    void updateNickname(@Param("userId") Integer userId,@Param("avatar") String avatar,@Param("nickName") String nickName,@Param("gender") Integer gender);
    //手机号码查询userid
    Integer findUserIdByPhoNo(String mobile);
    //userid查询token
    String findTokenByUserId(Integer userId);

    Integer findUserIdByOpenId(String openId);
    //手机验证
    void updatePhoStatus(@Param("userId") Integer userId ,@Param("mobile") String mobile,@Param("ifphover") Integer ifphover);
    //更新账户余额
    void updateAmount( UserDetailsDTO userDetailsDTO);

    //下线
    void offline(Integer userid);

    //找出用户常玩游戏
    Integer findOftenGame(Integer userid);

}