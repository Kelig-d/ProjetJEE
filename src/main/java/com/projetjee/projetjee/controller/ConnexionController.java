package com.projetjee.projetjee.controller;


import com.projetjee.projetjee.config.jdbcConfig;
import com.projetjee.projetjee.utils.jdbcUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Objects;


@Controller
public class ConnexionController {

    @GetMapping("/login")
    public String greetingForm(Model model) {
        model.addAttribute("logStat", new Connexion());
        System.out.println("here 2");
        return "connexion";
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID");
        serializer.setCookiePath("/");
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return serializer;
    }

    @PostMapping("/login")
    public String greetingSubmit(@ModelAttribute Connexion connexion, Model model) {
        jdbcConfig conf = new jdbcConfig();
        DataSource dataSource = conf.mysqlDataSource();
        model.addAttribute("logStat", connexion);

        boolean error = false;
        try {
            Connection conn = dataSource.getConnection();
            ResultSet rs = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM User");
            Map<String, String>[] User = jdbcUtils.resultToArray(rs);

            for (Map<String, String> stringStringMap : User) {
                System.out.println(stringStringMap);
                if(Objects.equals(stringStringMap.get("login"), connexion.getId()) && Objects.equals(stringStringMap.get("password"), connexion.getMdp())){
                  /*  UserDetails user = User.withDefaultPasswordEncoder()
                            .username("user")
                            .password("password")
                            .roles("user")
                            .build();
                    System.out.println(user.getPassword());*/
                    return "result";
                }
            }



        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return "connexion";
    }

}