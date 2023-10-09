package com.projetjee.projetjee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class ProjetJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetJeeApplication.class, args);
    }

    @GetMapping("/test")
    public String test(){
        return "Test";
    }

    @GetMapping("/other")
    public String other(){
        return "other";
    }

}
