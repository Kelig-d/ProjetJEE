package com.projetjee.projetjee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {


    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/site")
    public String sitePage(){
        return "site";
    }

    @GetMapping("/discipline")
    public String disciplinePage(){
        return "discipline";
    }
}
