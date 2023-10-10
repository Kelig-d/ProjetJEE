package com.projetjee.projetjee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@RestController
public class ProjetJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetJeeApplication.class, args);
    }

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
