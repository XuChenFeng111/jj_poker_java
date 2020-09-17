package com.shengming.entity.result;

/**
 * 基础枚举接口
 * @author XuChenFeng
 * @Date 2020/8/13 13:39
 */
public interface BaseEnum<K, V> {

    /**
     * 获取编码
     *
     * @return 编码
     */
    K code();

    /**
     * 获取描述
     *
     * @return 描述
     */
    V desc();

}