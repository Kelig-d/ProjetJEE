package com.projetjee.projetjee.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projetjee.projetjee.config.jdbcConfig;
import com.projetjee.projetjee.entities.JWebToken;
import com.projetjee.projetjee.entities.Utilisateur;
import com.projetjee.projetjee.repository.UtilsateurRepository;
import com.projetjee.projetjee.services.UtilisateurService;
import com.projetjee.projetjee.services.impl.UtilisateurImpl;
import com.projetjee.projetjee.utils.jdbcUtils;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jshell.execution.Util;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.io.Console;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.Cookie;

@RestController
public class MainController {



    @GetMapping("/other")
    public String other(){
        return "other";
    }

}
