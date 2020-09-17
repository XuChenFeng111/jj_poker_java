package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.UserMapper;
import com.shengming.entity.TokenDTO;
import com.shengming.entity.UserDTO;
import com.shengming.entity.result.Result;
import com.shengming.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;


/**
 * 手机发送验证码
 *
 * @author XuChenFeng
 * @Date 2020/8/14 10:10
 */
@Configuration
@RestController
public class SecurityCodeController {
    @Autowired
    private SecurityCodeUtils securityCodeUtils;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerTokenUtils verTokenUtils;
    //发送验证码时间
    private Long sendSMSTime = 0L;
    //填写验证码时间
    private Long writeSMSTime = 0L;

    @RequestMapping("/sendSmsMsg")
    public Result sendSmsMsg(String phoNo) {
        //校验手机号码
        boolean validPhoneNum = IdentityPhoNoUtils.validPhoneNum("0", phoNo);
        if (validPhoneNum) {
            //发送验证码
            String code = securityCodeUtils.SendSecurityCode(phoNo);
            sendSMSTime = System.currentTimeMillis();
            if (code != null) {
                return ResultsUtils.successWithData(code, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
            } else {
                return ResultsUtils.successWithData(code, "201", "验证码为空");
            }
        } else {
            return ResultsUtils.successWithData("","201","手机号码不正确，请输入正确的手机号码");
        }
    }


    /**
     * code 输入的验证码
     */
    @RequestMapping("/checkSecurityCode")
    public Result checkSecurityCode(String code, String phoNo) {
        //验证填入的手机号码和验证码 0：成功 ；1：失败
        String checkSecurityCode = securityCodeUtils.checkSecurityCode(code, phoNo);
        //验证成功
        if (checkSecurityCode.equals("0")) {
            writeSMSTime = System.currentTimeMillis();
            //验证码有效期1分钟
            if (writeSMSTime - sendSMSTime <= 60000) {
                String ifPho = userMapper.ifPho(phoNo);
                String openid = userMapper.ifOpenidByMobile(phoNo);
                //手机号码和openid为空 判断为手机首次登陆
                if (ifPho == null && openid == null) {
                    //随机生成token
                    String token = UUID.randomUUID().toString().replace("-", "");
                    // 将手机号码 token等信息 存储数据库
                    UserDTO userDTO = new UserDTO();
                    userDTO.setToken(token);
                    userDTO.setOpenid("mobile");
                    //随机生成昵称
                    String nickname = RandomUtils.verifyUserName(3, 6);
                    //判断昵称是否存在
                    String ifNickname = userMapper.ifNickname(nickname);
                    if (ifNickname == null) {
                        userDTO.setNickname(nickname);
                        userDTO.setMobile(phoNo);
                        userDTO.setLogonType(0);
                        userDTO.setIsrn(0);
                        userDTO.setPoint(0);
                        userDTO.setGoldcoin(0);
                        userDTO.setFukanum(0);
                        userDTO.setLevel(1);
                        userDTO.setRegtime(new Timestamp(new Date().getTime()));
                        userDTO.setIfphover(1);
                        userDTO.setIsvip(0);
                        userDTO.setGender(1);
                        userDTO.setAmount(0);
                        //生成新用户 插入数据库
                        userMapper.insert(userDTO);

                        Integer userId = userMapper.findUserIdByPhoNo(phoNo);
                        TokenDTO tokenDTO = new TokenDTO();
                        //查询出新用户的token和userid 进行返回
                        tokenDTO.setToken(token);
                        tokenDTO.setUserId(userId);
                        return ResultsUtils.successWithData(tokenDTO, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
                    } else {
                        return ResultsUtils.successWithData("","201","昵称已存在");
                    }
                } else if (ifPho != null && openid.equals("mobile")) { //手机登陆 非第一次
                    //随机生成token
                    String token = UUID.randomUUID().toString().replace("-", "");
                    userMapper.updateToken(token, phoNo);
                    Integer userId = userMapper.findUserIdByPhoNo(phoNo);
                    TokenDTO tokenDTO = new TokenDTO();
                    //查询出新用户的token和userid 进行返回
                    tokenDTO.setToken(token);
                    tokenDTO.setUserId(userId);
                    return ResultsUtils.successWithData(tokenDTO, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
                }
            } else {
                return ResultsUtils.successWithData("","201","验证码过期");
            }
        }
        return ResultsUtils.successWithData("","201","登陆失败,验证码或手机号码不正确");
    }

    /**
     * 手机验证
     *
     * @param code
     * @param phoNo
     * @param userId
     * @param token
     * @return
     */
    @RequestMapping("/verPho")
    public Result verPho(String code, String phoNo, Integer userId, String token) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userId);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            //验证填入的手机号码和验证码 0：成功 ；1：失败
            String checkSecurityCode = securityCodeUtils.checkSecurityCode(code, phoNo);
            //验证成功
            if (checkSecurityCode.equals("0")) {
                writeSMSTime = System.currentTimeMillis();
                //验证码有效期1分钟
                if (writeSMSTime - sendSMSTime <= 60000) {
                    userMapper.updatePhoStatus(userId, phoNo, 1);
                    return ResultsUtils.successWithData("",BaseEnums.SUCCESS.code(),"手机验证成功");
                } else {
                    return ResultsUtils.successWithData("","201","验证码过期");
                }
            }
            return ResultsUtils.successWithData("","201","登陆失败,验证码或手机号码不正确");
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }

}
