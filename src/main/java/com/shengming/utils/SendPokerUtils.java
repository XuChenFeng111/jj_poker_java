package com.shengming.utils;

import com.shengming.dao.UserMapper;
import com.shengming.entity.CardDTO;
import com.shengming.entity.UserDetailsDTO;
import com.shengming.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author XuChenFeng
 * @Date 2020/9/11 14:06
 */
@Component
public class SendPokerUtils {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoomService roomService;

    public Map<String, List<CardDTO>> sendPoker(Integer userid) {
        Map<Integer, CardDTO> cords = new HashMap<>(54);
        List<Integer> lists = new ArrayList<>();
        // 定义13个点数的数组
        String[] numbers = {"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
        // 定义4个花色
        String[] colors = {"hx", "mh", "ht", "fk"};
        int index = 2;
        for (String number : numbers) {
            for (String color : colors) {
                CardDTO cardDTO = CardDTO.builder().color(color).num(number).build();
                cords.put(index, cardDTO);
                lists.add(index);
                index++;
            }
        }
        cords.put(0, CardDTO.builder().num("JOKER").build());
        cords.put(1, CardDTO.builder().num("joker").build());
        lists.add(0);
        lists.add(1);

        Collections.shuffle(lists);

        ArrayList<Integer> palyer1 = new ArrayList<Integer>();
        ArrayList<Integer> palyer2 = new ArrayList<Integer>();
        ArrayList<Integer> palyer3 = new ArrayList<Integer>();
        ArrayList<Integer> bottomCords = new ArrayList<Integer>();
        for (int i = 0; i < lists.size(); i++) {
            if (i < 3) {
                bottomCords.add(lists.get(i));
            } else if (i % 3 == 0) {
                palyer1.add(lists.get(i));
            } else if (i % 3 == 1) {
                palyer2.add(lists.get(i));
            } else {
                palyer3.add(lists.get(i));
            }
        }

        String userIds = roomService.findUserIds(userid.toString());
        String[] str = userIds.split(",");
        String userid1 = Arrays.asList(str).get(0);
        String userid2 = Arrays.asList(str).get(1);
        String userid3 = Arrays.asList(str).get(2);
//        String player1 = getNickName(userid1);
//        String player2 = getNickName(userid2);
//        String player3 = getNickName(userid3);

        Map<String, List<CardDTO>> map = new HashMap<>(4);
        map.put("dipai", lookCard(bottomCords, cords));
        map.put(userid1, lookCard(palyer1, cords));
        map.put(userid2, lookCard(palyer2, cords));
        map.put(userid3, lookCard(palyer3, cords));

        for (Map.Entry<String, List<CardDTO>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("-------------------------------------");
        }
        return map;
    }


    public static List<CardDTO> lookCard(List<Integer> list, Map<Integer, CardDTO> map) {
        List<CardDTO> result = new ArrayList<>();
        Collections.sort(list);
        for (Integer integer : list) {
            result.add(map.get(integer));
        }
        return result;
    }

    private String getNickName(Integer userid) {
        UserDetailsDTO userDetailsDTO = userMapper.selectByPrimaryKey(userid);
        String nickname = userDetailsDTO.getNickname();
        return nickname;
    }
}

