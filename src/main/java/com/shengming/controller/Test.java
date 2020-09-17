package com.shengming.controller;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author XuChenFeng
 * @Date 2020/8/28 14:49
 */
@RestController
public class Test {

    public static void main(String[] args) {
        String str = "name=zhangsan&card=1";
        getValue("name", str);
    }

    /*从接收的字符串信息中，取出参数的值
     *name是参数名，str是接收到的字符串
     */
    public static String getValue(String name, String str) {
        String[] values = str.split("&");
        for (int i = 0; i < values.length; i++) {
            String[] val = values[i].split("=");
            System.out.println(val[1]);
            if (val[0].equalsIgnoreCase(name)) return val[1];
        }
        return null;
    }
}


