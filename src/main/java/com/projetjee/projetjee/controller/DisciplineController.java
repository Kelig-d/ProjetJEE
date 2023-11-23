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
public class DisciplineController {

    @Autowired private DisciplineService disciplineService;

    @GetMapping("/discipline")
    public String showDisciplineList(Model model) {
        model.addAttribute("disciplines", disciplineService.findAll());
        return "discipline";
    }

    // create new discipline
    // Save Discipline
    @PostMapping("/discipline/addDiscipline")
    public String saveDiscipline(@RequestParam("nameDiscipline") String name)
    {
        disciplineService.saveDiscipline(name,true);
        return "redirect:/discipline";
    }

    // Update discipline
    @PostMapping("/discipline/editDiscipline")
    public String updateDiscipline(@RequestParam("id_discipline") Long id_discipline, @RequestParam("nameDiscipline") String name)
    {
        disciplineService.updateDiscipline(id_discipline, name,true);
        return "redirect:/discipline";
    }

    // Delete discipline with epreuve
    @GetMapping("/discipline/delete/{id}")
    public String deleteDisciplineById(@PathVariable("id") Long id_discipline)
    {
        disciplineService.deleteDisciplineById(id_discipline);
        return "redirect:/discipline";
    }
}
