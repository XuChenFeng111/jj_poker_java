package com.shengming.dao;


import org.apache.ibatis.annotations.Param;


public interface LotteryMapper {
    boolean update_lottery(@Param("userid") Integer userid,@Param("point")  Integer point, @Param("goldcoin") Integer goldcoin);



}
