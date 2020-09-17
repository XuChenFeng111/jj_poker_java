package com.shengming.utils;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @author XuChenFeng
 * @Date 2020/9/2 17:39
 */
@Component
public class CountDiffTimeUtils {

    /**
     * 计算时间差 毫秒值
     *
     * @param date1
     * @return
     */
    public long countTime(Date date1) {
        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(new Date());    //设置为当前系统时间
        dateTwo.setTime(date1);    //获取数据库中的时间
        long timeOne = dateOne.getTimeInMillis();
        long timeTwo = dateTwo.getTimeInMillis();
        long diffTime = (timeOne - timeTwo);//获取毫秒差值
        return diffTime;
    }
}
