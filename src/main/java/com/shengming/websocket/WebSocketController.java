package com.shengming.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author XuChenFeng
 * @Date 2020/9/14 14:31
 */
@RestController
public class WebSocketController {
    @Autowired
    WebSocket webSocket;


    public static final Integer MSG_TYPE_ONE = 1; //抢地主
    public static final Integer MSG_TYPE_TWO = 2; //加倍
    public static final Integer MSG_TYPE_THREE = 3;  //出牌


    @ResponseBody
    @GetMapping("/sendTo")
    public String sendTo(@RequestParam("userId") String userId, @RequestParam("msg") String msg) throws IOException {

        webSocket.sendMessageTo(msg, userId);

        return "推送成功";
    }


    @ResponseBody
    @PostMapping("/sendAll")
    public String sendAll(@RequestParam("msg") String msg, @RequestParam("fromUserId") String fromUserId) throws IOException {
//        String fromUserId="system";//其实没用上
        webSocket.sendMessageAll(msg, fromUserId);
        return "推送成功";
    }


    /**
     * @param userId   用户id
     * @param funcType 操作类型
     * @param msg      是否操作或者出的牌 0：操作   1：未操作
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam("userId") String userId, @RequestParam("funcType") Integer funcType, @RequestParam("msg") String msg) throws IOException {
        //抢地主
        if (funcType.equals(MSG_TYPE_ONE)) {
            webSocket.sendMessage(msg, userId);
            if (msg.equals("0")) {
                return userId + " " + "抢地主";
            } else {
                return userId + " " + "不抢地主";
            }
        }
        //加倍
        if (funcType.equals(MSG_TYPE_TWO)) {
            webSocket.sendMessage(msg, userId);
            if (msg.equals("0")) {
                return userId + " " + "加倍";
            } else {
                return userId + " " + "不加倍";
            }
        }
        //出牌
        if (funcType.equals(MSG_TYPE_THREE)) {
            webSocket.sendMessage(msg, userId);
            return userId + " " + msg;
        }
        return userId + msg;
    }

}
