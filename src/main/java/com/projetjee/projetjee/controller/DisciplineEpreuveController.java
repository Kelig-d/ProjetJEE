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

    @GetMapping("/disciplineEpreuve")
    public String showDisciplineEpreuveList(Model model) {
        model.addAttribute("disciplines", disciplineService.findAllEpreuveDiscipline());
        return "disciplineEpreuve";
    }

    @GetMapping("/disciplineEpreuve/editEpreuve/{id}")
    public String showEpreuveList(@PathVariable(value = "id") long id_discipline, Model model) {
        model.addAttribute("epreuves",epreuveService.findAllEpreuveByDiscipline(id_discipline));
        return "editEpreuve";
    }

    @ResponseBody
    @GetMapping("/editedEpreuve/{id}")
    public ResponseEntity<JSONObject>showEpreuveList(@PathVariable(value = "dis") String dis, Model model){
        JSONObject response = new JSONObject();
        response.put("epreuves", epreuveService.getByDiscipline(dis));
        return ResponseEntity.ok(response);
    }

    // Save Discipline
    @PostMapping("/add")
    public Discipline saveDiscipline(@RequestParam("nameDiscipline") String name)
    {
        return disciplineService.saveDiscipline(name,true);
    }
    // Save Epreuve
    @PostMapping("/addEpreuve")
    public Epreuve saveEpreuve(@RequestParam("nameEpreuve") String name, @RequestParam("nameDiscipline") String discipline, Model model)
    {
        Discipline discipline1 = new Discipline();
        discipline1=disciplineService.findDisciplineByNom(discipline);
        model.addAttribute("epreuves",epreuveService.findAllEpreuveByDiscipline(discipline1.getId_discipline()));
        return epreuveService.saveEpreuve(name,discipline1);
    }

    // Update discipline
    @PostMapping("/editdiscipline")
    public String updateDiscipline(@RequestParam("id_discipline") Long id_discipline, @RequestParam("nameDiscipline") String name)
    {
        disciplineService.updateDiscipline(id_discipline, name,true);
        return "redirect:/disciplineEpreuve";
    }

    // Delete discipline with epreuve
    @GetMapping("/delete/{id}")
    public String deleteDisciplineById(@PathVariable("id") Long id_discipline)
    {
        disciplineService.deleteDisciplineById(id_discipline);
        return "redirect:/disciplineEpreuve";
    }

}
