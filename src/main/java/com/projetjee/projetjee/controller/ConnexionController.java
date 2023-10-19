package com.projetjee.projetjee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ConnexionController {

    @GetMapping("/login")
    public String greetingForm(Model model) {
        model.addAttribute("logStat", new Connexion());
        return "connexion";
    }

    @PostMapping("/login")
    public String greetingSubmit(@ModelAttribute Connexion connexion, Model model) {
        model.addAttribute("logStat", connexion);
        return "result";
    }

}