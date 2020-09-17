package com.shengming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shengming.dao")
//@ComponentScan(basePackages = {"com.shengming.service","com.shengming.controller","com.shengming.service.impl"})
public class JiangjiangpokerApplication {

    public static void main(String[] args) {
        //SpringApplication.run(JiangjiangpokerApplication.class, args);
        SpringApplication application = new SpringApplication(JiangjiangpokerApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
