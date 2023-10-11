package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.config.jdbcConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class MainController {
    @GetMapping("/test")
    public String test() {
        jdbcConfig conf = new jdbcConfig();
        DataSource dataSource = conf.mysqlDataSource();
        try {
            Connection conn = dataSource.getConnection();
            if(conn.isValid(500)) return "success";

        }
        catch (Exception e){
            System.out.println(e);
        }
        return "failure";
    }

    @GetMapping("/other")
    public String other(){
        return "other";
    }

}
