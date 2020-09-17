package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.AttendanceMapper;
import com.shengming.dao.UserMapper;
import com.shengming.entity.Attendance;
import com.shengming.entity.result.Result;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.VerTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author donggege
 * @Date
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceMapper attendanceMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    VerTokenUtils verTokenUtils;

    //continuousDay 连续签到数
//    public int continuousDay = 0;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @InitBinder
    protected void init(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /*
     * 签到保存接口
     * */
    @RequestMapping(value = "/insattendance", method = RequestMethod.POST)
    @ResponseBody
    public Result insattendance(Integer userId, Date signtime, Integer continuousdays, String prize, String token) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userId);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            int res = 0;
            res = attendanceMapper.insert(userId, signtime, continuousdays, prize);
            if (res == 1) {
                return ResultsUtils.successWithData("", BaseEnums.SUCCESS.code(), "签到成功");
            } else {
                return ResultsUtils.failureWithData("", "201", "签到失败");
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }

    /*
     * 移动端根据接口判断是连续第几天签到，周期为7天
     * 以签到第一天日期为基准，用当天日期-1，判断是连续签到，还是从头开始
     *
     * */

    public Result getsign(Integer userId) throws ParseException {
        int continuousDay = 0;
        List<Attendance> list = attendanceMapper.list(userId);

        Map<String, String> map = getWeekDate();
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + ":" + value);
        }

        if (list.size() == 0) {
            //等于null代表 从来没有签到过 进行第一次签到
            return null;
        } else {
            //有过签到记录 获取最大日期值 与当天日期进行比较
            //当前日期=数据表中最大值日期 代表 当天已经签到
            //判断签到周期7天 ？？
            //---------------------------------------------------------------

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("日期" + formatter.format(list.get(0).getSigntime()));

            //获取连续签到多少天
            for (int i = 0; i < list.size(); i++) {
                if (list.size() == 1) {
                    continuousDay = 1;
                } else {
                    if ((distanceDay(formatter.parse(gettoday()), list.get(i).getSigntime())) == continuousDay) {
                        continuousDay++;
                    } else {
                        break;
                    }
                }
            }
            System.out.println("天数" + continuousDay);

            if (gettoday().compareTo(formatter.format(list.get(0).getSigntime())) == 0) {
                System.out.println("当前日期" + gettoday() + "数据表日期" + formatter.format(list.get(0).getSigntime()));
                return ResultsUtils.successWithData("", "201", "当天已经签到,连续签到" + continuousDay + "天");
            } else {

            }
            return null;
        }
    }

    @RequestMapping(value = "/getsign", method = RequestMethod.POST)
    public Result getsign2(Integer userId, String token) throws ParseException {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userId);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            //先获取当前日期所在周期的第一天和最后一天 根据用户id 查询出这一周期的签到情况
            Map<String, String> mapdate = getWeekDate();
            List<Attendance> listfordate = attendanceMapper.listfordate(userId, mapdate.get("mondayDate").toString(), mapdate.get("sundayDate").toString());
            if (listfordate.size() == 0) {
                //说明这周都没有开始签到 开始进行这周第一次签到  默认连续签到日期为1天  金豆为默认值
                //return ResultsUtils.failure("本周还没有签到,连续签到"+get_sign_day(listfordate)+"天");
                return ResultsUtils.successWithData(get_map_sign_day_prize(get_sign_day(listfordate, gettoday()), "10", true), BaseEnums.SUCCESS.code(), "本周还没有签到");
            } else {
                //说明已经有签到记录
                // 1、判断当天有没有签到 2、是否是连续签到 返回连续签到天数 如果是连续签到 金豆累加 反之返回初始值
                //判断当天有没有签到
                if (gettoday().compareTo(listfordate.get(0).getSigndate()) == 0) {
                    //代表当天已经签到
                    return ResultsUtils.successWithData(get_map_sign_day_prize(get_sign_day(listfordate, gettoday()), listfordate.get(0).getPrize(), false), BaseEnums.SUCCESS.code(), "当天已经签到");
                } else {
                    //没有签到  则进行签到
                    if ((distanceDay(formatter.parse(gettoday()), formatter.parse(listfordate.get(0).getSigndate()))) == 1) {//判断即将签到日期是否是连续日期
                        //代表连续签到 金豆调取本周最大值
                        return ResultsUtils.successWithData(get_map_sign_day_prize(get_sign_day(listfordate, get_yesterday()), listfordate.get(0).getPrize(), true), BaseEnums.SUCCESS.code(), "当天未签到,本次签到则属于连续签到");
                    } else {
                        return ResultsUtils.successWithData(get_map_sign_day_prize(get_sign_day(listfordate, gettoday()), "10", false), BaseEnums.SUCCESS.code(), "当天未签到,本次签到属于断签");
                    }
                }
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }


    //获取当前日期
    private String gettoday() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(calendar.getTime());
    }

    //获取昨天日期
    private String get_yesterday() {
        Calendar calendar = new GregorianCalendar();

        calendar.setTime(new Date());

        calendar.add(calendar.DATE, -1);

        String date2 = formatter.format(calendar.getTime());

        System.out.println(date2);

        return date2;
    }

    //判断当天日期 与以往签到日期相隔天数
    private static int distanceDay(Date largeDay, Date smallDay) {
        int day = (int) ((largeDay.getTime() - smallDay.getTime()) / (1000 * 60 * 60 * 24));
        return day;
    }


    //获取签到天数
    private int get_sign_day(List<Attendance> list, String date) throws ParseException {
        int continuousDay = 0;
        if (list.size() == 0) {
            continuousDay = 0;
        } else {
            for (int i = 0; i < list.size(); i++) {
                //date是指当天日期
                //distanceDay 判断当天日期 与以往签到日期相隔天数
                if ((distanceDay(formatter.parse(date), formatter.parse(list.get(i).getSigndate()))) == continuousDay) {
                    continuousDay++;
                } else {
                    break;
                }
            }
        }
        return continuousDay;
    }

    /**
     * 获取当前时间所在周的周一和周日的日期时间
     *
     * @return
     */
    public static Map<String, String> getWeekDate() {
        Map<String, String> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 8;
        }

        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);

        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);

        map.put("mondayDate", weekBegin);
        map.put("sundayDate", weekEnd);
        return map;
    }

    //封装天数，金豆数据
    private Map<String, Object> get_map_sign_day_prize(int day, String prize, boolean is_Sign_in) {
        Map<String, Object> map = new HashMap<>();
        map.put("countday", String.valueOf(day));
        map.put("prize", prize);
        map.put("is_Sign_in", is_Sign_in);
        return map;
    }


}
