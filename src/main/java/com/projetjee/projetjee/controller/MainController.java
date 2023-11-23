package com.projetjee.projetjee.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {





    @GetMapping("/other")
    public String other(){
        return "other";
    }

}
