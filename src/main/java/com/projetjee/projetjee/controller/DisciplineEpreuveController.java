package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.services.DisciplineService;
import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.entities.Epreuve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller 
public class DisciplineEpreuveController {

    @Autowired private DisciplineService disciplineService;
    @Autowired private DisciplineService epreuveService;

    /**
     * {@code GET /disciplineEpreuve} : get all the discipline.
     *
     * @return
     */
    @GetMapping("/disciplineEpreuve")
    public String showUserList(Model model) {
        model.addAttribute("disciplines", disciplineService.findAll());
        return "disciplineEpreuve";
    }
    // Save Discipline
    @PostMapping("/add")
    public String saveDiscipline(@RequestParam("nameDiscipline") String name)
    {
        disciplineService.saveDiscipline(name,true);
        return "redirect:/disciplineEpreuve";
    }

    // Update operation
    @PostMapping("/edit")
    public String updateDiscipline(@RequestParam("id_discipline") Long id_discipline, @RequestParam("nameDiscipline") String name)
    {
        disciplineService.updateDiscipline(id_discipline, name,true);
        return "redirect:/disciplineEpreuve";
    }

    // Delete operation
    @GetMapping("/delete/{id}")
    public String deleteDisciplineById(@PathVariable("id") Long id_discipline)
    {
        disciplineService.deleteDisciplineById(id_discipline);
        return "redirect:/disciplineEpreuve";
    }

}
