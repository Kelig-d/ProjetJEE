package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.config.jdbcConfig;
import com.projetjee.projetjee.utils.jdbcUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {
    @GetMapping("/test")
    public Map<String, String > test() {
        jdbcConfig conf = new jdbcConfig();
        DataSource dataSource = conf.mysqlDataSource();
        try {
            Connection conn = dataSource.getConnection();
            ResultSet rs = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM test");
            return jdbcUtils.resultToArray(rs);


        }
        catch (Exception e){
            System.out.println(e);
            Map<String, String> ret = new HashMap<>();
            ret.put("result","fail");
            return ret;
        }
    }

    @GetMapping("/other")
    public String other(){
        return "other";
    }

}
