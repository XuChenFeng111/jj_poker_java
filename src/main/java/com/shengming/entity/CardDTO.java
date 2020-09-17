package com.shengming.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunjindong
 * @create 2020/9/11 17:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {
    //点数
    private String num;
    //花色
    private String color;
    //名称
//    private String name;

    @Override
    public String toString() {
        if (num.equals("JOKER")) {
            return "JOKER";
        }
        if (num.equals("joker")) {
            return "joker";
        }
        return color + num;
    }

}
