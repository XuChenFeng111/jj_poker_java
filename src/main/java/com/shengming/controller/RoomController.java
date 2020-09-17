package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.UserMapper;
import com.shengming.entity.RoomDTO;
import com.shengming.entity.result.Result;
import com.shengming.service.RoomService;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.VerTokenUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/8 16:56
 */
@RestController
@RequestMapping("/game")
@Transactional
public class RoomController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    VerTokenUtils verTokenUtils;
    @Autowired
    private RoomService roomService;

    @RequestMapping("/startGame")
    public Result startGame(Integer userid, String token, Integer gametype) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
//            if (getInRoom(userid, gametype).equals("0")) {
            RoomDTO roomDTO = getInRoom(userid, gametype);
            if (roomDTO != null) {
                return ResultsUtils.successWithData(roomDTO, BaseEnums.SUCCESS.code(), "进入房间成功");
            } else {
                return ResultsUtils.successWithData("","201","进入房间失败");
            }
//            } else {
//                return ResultsUtils.failure("进入房间失败");
//            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }


    /**
     * 点击开始游戏 进入房间
     *
     * @param userid
     * @param gametype
     * @return 1 不成功 ;0  成功
     */
    public RoomDTO getInRoom(Integer userid, Integer gametype) {
//        String isok = "1";
        try {
            List<RoomDTO> roomDTOs = roomService.findAll(gametype);
            //没有可进房间
            if (roomDTOs.size() == 0) {
                RoomDTO roomDTO1 = new RoomDTO();
                roomDTO1.setGametype(gametype);
                roomDTO1.setPlayernum(userid + ",");
                roomService.insert(roomDTO1);
            } else {
                //判断该用户 是否已经在房间中
                List<Integer> ids = judgeIfInRoom(roomDTOs, userid.toString());
                if (ids.size() != 0) {
                    Integer id = ids.get(0);
                    String playernumFromDB = roomService.findPlayNumById(id);
                    String playernum = playernumFromDB + userid + ",";
                    roomService.updateNumById(id, playernum);

                    RoomDTO roomDTO = roomService.selectByPrimaryKey(id);
                    String playernum1 = roomDTO.getPlayernum();
                    String[] strs = playernum1.split(",");
                    if (strs.length == 3) {
                        return roomDTO;
                    }
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
//        isok = "0";
        return null;
    }


    /**
     * 判断该用户在数据库中是否存在房间内
     *
     * @param userid
     * @param gametype
     * @return 1 不成功 ;0  成功
     */
    public List<Integer> judgeIfInRoom(List<RoomDTO> all, String userid) {
        List<Integer> list = new ArrayList<>();
        for (RoomDTO roomDTO : all) {
            String playernum = roomDTO.getPlayernum();
            String[] userids = playernum.split(",");
            //是否包含该用户
            if (Arrays.asList(userids).contains(userid)) {
                return null;
            } else {
                list.add(roomDTO.getId());
                return list;
            }
        }
        return list;
    }
}
