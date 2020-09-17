package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.ActivitylistMapper;
import com.shengming.entity.result.Result;
import com.shengming.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author donggege
 * @create 2020-08-27 13:33
 */

@RestController
@RequestMapping("/activity_list")
public class ActivitylistController {
    @Autowired
    private ActivitylistMapper activitylistMapper;

    @RequestMapping("/select_all")
    public Result get_activity_list_all(){
        return ResultsUtils.successWithData(activitylistMapper.select_all(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
}
