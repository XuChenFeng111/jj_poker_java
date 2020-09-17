package com.shengming.service.impl;
import com.shengming.dao.FriendlistMapper;
import com.shengming.entity.FriendApplyDTO;
import com.shengming.entity.Friendlist;
import com.shengming.entity.UserDetailsDTO;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author donggege
 * @create 2020-08-28 16:50
 */
public class FriendlistImpl implements FriendlistMapper {

    @Resource
    private FriendlistMapper friendlistMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Integer userid, Integer friendid, Integer friendfrom) {
        return 0;
    }

    @Override
    public Integer findApplyStatus(Integer userid, Integer frinedid) {
        return friendlistMapper.findApplyStatus(userid,frinedid);
    }

    @Override
    public int insertSelective(Friendlist record) {
        return 0;
    }

    @Override
    public Friendlist selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Friendlist record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Integer userid) {
        return 0;
    }

    @Override
    public List<Friendlist> list_for_friend_id(Integer userid) {
        return friendlistMapper.list_for_friend_id(userid);
    }

    @Override
    public List<UserDetailsDTO> select_friend_list(Integer userid,String searchContent,List<Integer> list) {
        return friendlistMapper.select_friend_list(userid,searchContent,list);
    }

    @Override
    public List<UserDetailsDTO> select_all(Integer userid) {
        return friendlistMapper.select_all(userid);
    }

    @Override
    public List<UserDetailsDTO> select_all_me(Integer userid) {
        return friendlistMapper.select_all_me(userid);
    }

    @Override
    public List<FriendApplyDTO> findApplyList(Integer userid) {
        return friendlistMapper.findApplyList(userid);
    }

    public void updateApplyStatus(Integer userid,Integer applystatus,Integer friendid){
         friendlistMapper.updateApplyStatus(userid,applystatus,friendid);
    }

    public void  deleteByStatus(Integer userid,Integer friendid){
        friendlistMapper.deleteByStatus(userid,friendid);
    }


}
