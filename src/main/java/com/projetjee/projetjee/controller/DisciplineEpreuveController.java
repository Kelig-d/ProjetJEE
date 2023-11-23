package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.entities.*;
import com.projetjee.projetjee.services.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return findPaginated(1,  model);
    }

    @GetMapping("/disciplineEpreuve/{pageNb}")
    public String findPaginated(@PathVariable(value="pageNb") int pageNb, Model model ){
        int pageSize = 10;
        Page<Discipline> page = disciplineService.findPaginatedDsicEpr(pageNb,pageSize);
        List<Discipline> disciplines = page.getContent();
        model.addAttribute("currentPage", pageNb);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());


        model.addAttribute("disciplines", disciplines);
        return "/disciplineEpreuve";
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
