package com.shengming.dao;

import com.shengming.entity.FriendApplyDTO;
import com.shengming.entity.Friendlist;
import com.shengming.entity.UserDetailsDTO;
import com.shengming.entity.UserDetailsDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FriendlistMapper {
    int deleteByPrimaryKey(Integer id);
    //点击添加好友，往数据库插一条数据
    int insert(@Param("userid") Integer userid,@Param("friendid")  Integer friendid, @Param("friendfrom") Integer friendfrom);

    Integer findApplyStatus(@Param("userid") Integer userid,@Param("friendid") Integer frinedid);
    int insertSelective(Friendlist record);

    Friendlist selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friendlist record);
    // 好友还未通过，再一次点击价位好友，更新添加好友时间
    int updateByPrimaryKey(@Param("userid") Integer userid);

    List<Friendlist> list_for_friend_id(Integer userid);

    //获取除自己本身 和 未加好友 数据列表
//    List<UserDetailsDTO> select_friend_list(@Param("userid") Integer userid,@Param("mobile") String mobile,@Param("nickName") String nickName,@Param("list") List<Integer> list);
    List<UserDetailsDTO> select_friend_list(@Param("userid") Integer userid,@Param("searchContent") String searchContent,@Param("list") List<Integer> list);

    List<UserDetailsDTO> select_all(Integer userid);
    //获取自己已加好友列表
    List<UserDetailsDTO> select_all_me( Integer userid);
    //获取自己已加好友列表
    List<FriendApplyDTO> findApplyList(Integer userid);

    void updateApplyStatus(@Param("userid") Integer userid,@Param("applystatus") Integer applystatus,@Param("friendid") Integer friendid);

    void deleteByStatus(@Param("userid") Integer userid,@Param("friendid") Integer friendid);

}