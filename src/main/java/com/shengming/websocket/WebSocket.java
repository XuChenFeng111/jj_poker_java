package com.shengming.websocket;

/**
 * @author XuChenFeng
 * @Date 2020/9/14 13:55
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.shengming.constants.BaseEnums;
import com.shengming.entity.CardDTO;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.SendPokerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author：JCccc
 * @Description：
 * @Date： created in 15:56 2019/5/13
 */

@Component
@ServerEndpoint(value = "/connectWebSocket/{userId}")
public class WebSocket {

    static SendPokerUtils sendPokerUtils;

    @Autowired
    public void setSendPokerUtils(SendPokerUtils sendPokerUtils) {
        WebSocket.sendPokerUtils = sendPokerUtils;
    }


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户名称
     */
    private String userId;

    public static final Integer MSG_TYPE_ONE = 1; //抢地主
    public static final Integer MSG_TYPE_TWO = 2; //加倍
    public static final Integer MSG_TYPE_THREE = 3;  //出牌


    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        onlineNumber++;
        logger.info("现在来连接的客户id：" + session.getId() + "用户名：" + userId);
        this.userId = userId;
        this.session = session;
        //  logger.info("有新连接加入！ 当前在线人数" + onlineNumber);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
            //先给所有人发送通知，说我上线了
            Map<String, Object> map1 = Maps.newHashMap();
            map1.put("funcType", 4);
            map1.put("userId", userId);
            map1.put("msg", "加入房间");
            clients.put(userId, this);
            map1.put("onlineUsers", clients.size());
            sendMessageAll(JSON.toJSONString(map1), userId);
            logger.info("有连接关闭！ 当前在线人数" + clients.size());
        } catch (IOException e) {
            logger.info(userId + "上线的时候通知所有人发生了错误");
        }


    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("服务端发生了错误" + error.getMessage());
        //error.printStackTrace();
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        //webSockets.remove(this);
        clients.remove(userId);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = Maps.newHashMap();
//            map1.put("messageType", 2);
//            map1.put("onlineUsers", clients.keySet());
//            map1.put("userId", userId);
//            sendMessageAll(JSON.toJSONString(map1), userId);
        } catch (Exception e) {
            logger.info(userId + "下线的时候通知所有人发生了错误");
        }
        //logger.info("有连接关闭！ 当前在线人数" + onlineNumber);
        logger.info("有连接关闭！ 当前在线人数" + clients.size());
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            logger.info("来自客户端消息：" + message + "客户端的id是：" + session.getId());

            System.out.println("------------  :" + message);

            JSONObject jsonObject = JSON.parseObject(message);
            Integer userId = jsonObject.getInteger("userId");
            Integer funcType = jsonObject.getInteger("funcType");
            String msg = jsonObject.getString("msg");
            sendMessageAll(message, userId.toString());

            if (funcType.equals(5)) {
                Map<String, List<CardDTO>> stringListMap = sendPokerUtils.sendPoker(userId);
                Map<String, Object> map1 = Maps.newHashMap();
                map1.put("card", JSON.toJSONString(stringListMap));
                if (stringListMap != null) {
                    sendMessageAll(JSON.toJSONString(map1), userId.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("发生了错误了");
        }
    }


    public void sendMessageTo(String message, String TouserId) throws IOException {
        for (WebSocket item : clients.values()) {
            if (item.userId.equals(TouserId)) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    public void sendMessageAll(String message, String FromuserId) throws IOException {
        synchronized (session) {
            for (WebSocket item : clients.values()) {
//                item.session.getAsyncRemote().sendText(message);
                item.session.getBasicRemote().sendText(message);
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }


    public void sendMessage(String message, String TouserId) {
        for (WebSocket item : clients.values()) {
            // 排除掉自己
            if (!item.userId.equals(TouserId)) {
                System.out.println("服务端给客户端[{" + item.userId + "}]发送消息{" + message + "}");
                item.session.getAsyncRemote().sendText(message);
            }
        }
    }
}

