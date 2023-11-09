package com.projetjee.projetjee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {


    @GetMapping("/")
    public String homePage(){
        return "connexion";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/site")
    public String site(){
        return "site";
    }

    @GetMapping("/disc")
    public String dicsipline(){
        return "discipline";
    }

    @GetMapping("/session")
    public String session(){
        return "session";
    }

    @GetMapping("/stats")
    public String stats(){
        return "statistique";
    }


}