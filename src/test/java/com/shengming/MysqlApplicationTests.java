package com.shengming;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author XuChenFeng
 * @Date 2020/8/13 10:09
 */
public class MysqlApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbc() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        maps.forEach(System.out::println);
    }
}
