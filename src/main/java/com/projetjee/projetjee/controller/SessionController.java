package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.config.jdbcConfig;
import com.projetjee.projetjee.utils.jdbcUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {

    @GetMapping("/createSessionData")
    public ArrayList<String> sendCreateSessionData(){
        jdbcConfig conf = new jdbcConfig();
        DataSource dataSource = conf.mysqlDataSource();
        try {
            String sql = "SELECT nom FROM Discipline";
            Connection con = dataSource.getConnection();
            PreparedStatement prep = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = prep.executeQuery();
            Map<String, String>[] queryRes =  jdbcUtils.resultToArray(rs);
            ArrayList<String> res = new ArrayList<>();
            for(Map<String, String> row : queryRes){
                System.out.println(row);
            }
            return res;



        }
        catch (Exception e){
            System.out.println(e);
            Map<String, String>[] ret = new Map[1];
            ret[0] = new HashMap<String, String>();
            ret[0].put("result","fail");
            return new ArrayList<>();
        }
    }
}
