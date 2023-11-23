package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.entities.*;
import com.projetjee.projetjee.services.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller 
public class DisciplineEpreuveController {

    @Autowired private DisciplineService disciplineService;
    @Autowired private EpreuveService epreuveService;

    // Read discipline with epreuve
    @GetMapping("/disciplineEpreuve")
    public String showDisciplineEpreuveList(Model model) {
        model.addAttribute("disciplines", disciplineService.findAllEpreuveDiscipline());
        return "disciplineEpreuve";
    }


    // Delete discipline with epreuve
    @GetMapping("/delete/{id}")
    public String deleteDisciplineById(@PathVariable("id") Long id_discipline)
    {
        String present=null;
        Discipline dis = disciplineService.getDisciplineById(id_discipline);
        List<Epreuve> epreuvelist = epreuveService.getEpreuveByDiscipline(dis);
        Session  session = new Session();
        for (Epreuve epreuve : epreuvelist) {
            session = epreuveService.getSessionsByEpreuve(epreuve);
            if(session!=null){
                present="present";
            }
        }
        if (present == null) {
            disciplineService.deleteDisciplineById(id_discipline);
        }
        return "redirect:/disciplineEpreuve";
    }

}
