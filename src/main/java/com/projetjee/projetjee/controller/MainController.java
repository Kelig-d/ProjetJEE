package com.projetjee.projetjee.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @GetMapping("/other")
    public String other(){
        return "other";
    }

}
