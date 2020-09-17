package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.FriendlistMapper;
import com.shengming.dao.UserMapper;
import com.shengming.entity.FriendApplyDTO;
import com.shengming.entity.Friendlist;
import com.shengming.entity.UserDetailsDTO;
import com.shengming.entity.result.Result;
import com.shengming.utils.CountDiffTimeUtils;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.VerTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author donggege
 * @create 2020-08-28 16:45
 */
@RestController
@RequestMapping("/friendlist")
public class FriendlistController {

    @Autowired
    private FriendlistMapper friendlistMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerTokenUtils verTokenUtils;
    @Autowired
    private CountDiffTimeUtils countDiffTimeUtils;


//    @InitBinder
//    protected void init(ServletRequestDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//    }

    /**
     * 根据用户id查询未加好友列表
     *
     * @param userid
     * @return
     */
    @RequestMapping("/select_friend_list")
    public Result select_friend_list(Integer userid, String searchContent, String token) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            //获取已加好友的id
            List<Friendlist> list_friend = friendlistMapper.list_for_friend_id(userid);
            List<Integer> list_friend_id = new ArrayList<Integer>();
            if (list_friend.size() > 0) {
                for (int i = 0; i < list_friend.size(); i++) {
                    list_friend_id.add(list_friend.get(i).getFriendid());
                }
                //获取未加其他好友列表
                List<UserDetailsDTO> friend_list = friendlistMapper.select_friend_list(userid, searchContent, list_friend_id);
                return ResultsUtils.successWithData(friend_list, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
            } else {
                //除去他本身  其他所有用户
                return ResultsUtils.successWithData(friendlistMapper.select_all(userid), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }

    /**
     * 获取除自己本身 已加好友列表
     *
     * @param userid
     * @return
     */
    @RequestMapping("/select_friend_list_all")
    public Result select_frirnd_list_all(Integer userid, String token) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        List<UserDetailsDTO> UserDetailsDTOS = null;
        if (isok.equals("0")) {
            UserDetailsDTOS = friendlistMapper.select_all_me(userid);
            if (UserDetailsDTOS.size() != 0) {
                return ResultsUtils.successWithData(UserDetailsDTOS, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
            } else {
                return ResultsUtils.successWithData(UserDetailsDTOS, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }

    /**
     * 内部添加好友
     *
     * @param
     * @return
     */
    @RequestMapping("/insert_friend")
    public Result insert_friend(Integer userid, Integer friendid, Integer friendfrom, String token) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        Integer applyStatus = friendlistMapper.findApplyStatus(userid, friendid);
        if (isok.equals("0")) {
//            Date applytime = new Date();
            //点击添加好友，往数据库插一条数据
            if (applyStatus == null) {
                if (friendlistMapper.insert(userid, friendid, friendfrom) == 1) {
                    return ResultsUtils.successWithData("",BaseEnums.SUCCESS.code(),"请求添加好友成功");
                } else {
                    return ResultsUtils.successWithData("","201","请求添加好友失败");
                }
                // 好友还未通过，再一次点击加为好友，更新添加好友时间
            } else if (applyStatus == 1) {
                if (friendlistMapper.updateByPrimaryKey(userid) == 1) {
                    return ResultsUtils.successWithData("",BaseEnums.SUCCESS.code(),"重新请求添加好友成功");
                } else {
                    return ResultsUtils.successWithData("","201","重新请求添加好友失败");
                }
            } else {

            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
        return ResultsUtils.successWithData("",BaseEnums.SUCCESS.code(),"请求成功");
    }

    /**
     * 好友申请列表
     */
    @RequestMapping("/findApplyList")
    public Result findApplyList(Integer userId, String token) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userId);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        List<FriendApplyDTO> applyList = null;
        if (isok.equals("0")) {
            //获取自己已加好友列表
            applyList = friendlistMapper.findApplyList(userId);
            if (applyList.size() != 0) {
                for (FriendApplyDTO friendApplyDTO : applyList) {
                    Date applyTime = friendApplyDTO.getApplyTime();
                    long countTime = countDiffTimeUtils.countTime(applyTime);
                    long minutes = (countTime % (1000 * 60 * 60)) / (1000 * 60);
                    long hours = (countTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                    long days = countTime / (1000 * 60 * 60 * 24);
                    if (days > 0) {
                        friendApplyDTO.setApplyDiffTime(days + "天前申请");
//                    break;
                    } else if (0 < hours && hours < 24) {
                        friendApplyDTO.setApplyDiffTime(hours + "小时天前申请");
                    } else if (0 < minutes && minutes < 60) {
                        friendApplyDTO.setApplyDiffTime(minutes + "分钟天前申请");
                    } else {
                        friendApplyDTO.setApplyDiffTime("刚刚");
                    }
                }
                return ResultsUtils.successWithData(applyList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
            } else {
                return ResultsUtils.successWithData(applyList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
            }

        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }

    /**
     * 同意或拒绝添加好友
     */
    @RequestMapping("/agree_friends")
    public Result agree_friends(Integer userid, Integer ifagree, String token, Integer friendid) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            if (ifagree == 0) {
                //同意添加好友 更新数据库的申请状态
                friendlistMapper.updateApplyStatus(userid, 0, friendid);
                return ResultsUtils.successWithData("",BaseEnums.SUCCESS.code(),"好友添加成功");
            } else {
                //拒绝添加好友 更新数据库的申请状态
                friendlistMapper.updateApplyStatus(userid, 2, friendid);
                return ResultsUtils.successWithData("",BaseEnums.SUCCESS.code(),"好友拒绝成功");
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }


}
