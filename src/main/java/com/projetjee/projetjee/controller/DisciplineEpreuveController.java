package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.services.DisciplineService;
import com.projetjee.projetjee.entities.Discipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller 
public class DisciplineEpreuveController {

    @Autowired private DisciplineService disciplineService;

    /**
     * {@code GET /disciplineEpreuve} : get all the discipline.
     *
     * @return
     */
    @GetMapping("/disciplineEpreuve")
    /*public List<Discipline> fetchDisciplineList()
    {
        return disciplineService.fetchDisciplineList();
    }*/
    public String showUserList(Model model) {
        model.addAttribute("disciplines", disciplineService.fetchDisciplineList());
        return "disciplineEpreuve";
    }
    // Save Discipline
    @PostMapping("/disciplineEpreuve")
    public Discipline saveDiscipline(@RequestBody Discipline discipline)
    {
        return disciplineService.saveDiscipline(discipline);
    }

    // Update operation
    @PutMapping("/disciplineEpreuve/{id}")

    public Discipline updateDiscipline(@RequestBody Discipline discipline, @PathVariable("id") Long id_discipline)
    {
        return disciplineService.updateDiscipline(discipline, id_discipline);
    }

    // Delete operation
    @DeleteMapping("/disciplineEpreuve/{id}")

    public String deleteDisciplineById(@PathVariable("id") Long id_discipline)
    {
        disciplineService.deleteDisciplineById(id_discipline);
        return "Deleted Successfully";
    }

}
