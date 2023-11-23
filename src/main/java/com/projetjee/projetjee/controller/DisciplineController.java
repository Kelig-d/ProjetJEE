package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.entities.*;
import com.projetjee.projetjee.services.*;
import lombok.val;
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

    // Read all discipline
    @GetMapping("/discipline")
    public String showDisciplineList(Model model) {
        model.addAttribute("disciplines", disciplineService.findAll());
        return "discipline";
    }

    // Save Discipline
    @PostMapping("/discipline/addDiscipline")
    public String saveDiscipline(@RequestParam(value = "nameDiscipline") String name,@RequestParam("paralympique") Boolean para)
    {
        disciplineService.saveDiscipline(name, para);
        return "redirect:/discipline";

    }

    // Update discipline
    @PostMapping("/discipline/editdiscipline")
    public String updateDiscipline(@RequestParam("id_discipline") Long id_discipline, @RequestParam("nameDiscipline") String name,@RequestParam("paralympique") Boolean para)
    {
        disciplineService.updateDiscipline(id_discipline, name,para);
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
