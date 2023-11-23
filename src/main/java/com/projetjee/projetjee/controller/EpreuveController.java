package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.services.DisciplineService;
import com.projetjee.projetjee.services.EpreuveService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class EpreuveController {

    @Autowired private EpreuveService epreuveService;
    @Autowired private DisciplineService disciplineService;

    @GetMapping("/epreuve")
    public String showEpreuveList(Model model) {
        model.addAttribute("epreuves", epreuveService.findAll());
        return "epreuve";
    }

    // create new discipline
    // Save Discipline
    @PostMapping("/epreuve/addEpreuve")
    public String saveEpreuve(@RequestParam("nameEpreuve") String name,@RequestParam("choixdis") String discipline)
    {
        Discipline dis = disciplineService.getByNom(discipline);
        epreuveService.saveEpreuve(name,dis);
        return "redirect:/epreuve";
    }

    // Update discipline
    @PostMapping("/epreuve/editEpreuve")
    public String updateEpreuve(@RequestParam("id_epreuve") Long id_epreuve, @RequestParam("nameEpreuve") String name,@RequestParam("discipline") Discipline id_discipline)
    {
        epreuveService.updateEpreuve(id_epreuve, name,id_discipline);
        return "redirect:/epreuve";
    }

    // Delete discipline with epreuve
    @GetMapping("/epreuve/delete/{id}")
    public String deleteEpreuveById(@PathVariable("id") Long id_epreuve)
    {
        epreuveService.deleteEpreuveById(id_epreuve);
        return "redirect:/epreuve";
    }

    @ResponseBody
    @GetMapping("/epreuve/createSessionDisciplines")
    public ResponseEntity<JSONObject> sendCreateSessionDisciplines() {
        JSONObject response = new JSONObject();
        response.put("disciplines", disciplineService.getAllNames());
        return ResponseEntity.ok(response);
    }
}
