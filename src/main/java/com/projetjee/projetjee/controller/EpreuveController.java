package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.entities.Epreuve;
import com.projetjee.projetjee.entities.Session;
import com.projetjee.projetjee.services.DisciplineService;
import com.projetjee.projetjee.services.EpreuveService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EpreuveController {

    @Autowired private EpreuveService epreuveService;
    @Autowired private DisciplineService disciplineService;

    // Read all epreuve

    @GetMapping("/epreuve")
    public String showEpreuveList(Model model) {
        return findPaginated(1,  model);
    }

    // Save Epreuve
    @PostMapping("/epreuve/addEpreuve")
    public String saveEpreuve(@RequestParam("nameEpreuve") String name,@RequestParam("choixdis") String discipline)
    {
        Discipline dis = disciplineService.getByNom(discipline);
        epreuveService.saveEpreuve(name,dis);
        return "redirect:/epreuve";
    }

    // Update discipline
    @PostMapping("/epreuve/editEpreuve")
    public String updateEpreuve(@RequestParam("id_epreuve") Long id_epreuve, @RequestParam("nameEpreuve") String name,@RequestParam("nameDiscipline") String discipline)
    {
        Discipline dis = disciplineService.getByNom(discipline);
        epreuveService.updateEpreuve(id_epreuve, name,dis);
        return "redirect:/epreuve";
    }

    // Delete epreuve
    @GetMapping("/epreuve/delete/{id}")
    public String deleteEpreuveById(@PathVariable("id") Long id_epreuve)
    {
        List<Epreuve> epreuvelist = epreuveService.getEpreuveById(id_epreuve);
        Session  session = new Session();
        for (Epreuve epreuve : epreuvelist) {
            session= epreuveService.getSessionsByEpreuve(epreuve);
        }
        if(session==null){
            epreuveService.deleteEpreuveById(id_epreuve);
        }else{
            throw new RuntimeException(" Vous ne pouvez pas supprimer cette épreuve");
        }
        return "redirect:/epreuve";

    }

    @ResponseBody
    @GetMapping("/epreuve/disciplines")
    public ResponseEntity<JSONObject> sendCreateSessionDisciplines() {
        JSONObject response = new JSONObject();
        response.put("disciplines", disciplineService.getAllNames());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/epreuve/{pageNb}")
    public String findPaginated(@PathVariable(value="pageNb") int pageNb, Model model ){
        int pageSize = 10;
        Page<Epreuve> page = epreuveService.findPaginated(pageNb,pageSize);
        List<Epreuve> epreuves = page.getContent();
        model.addAttribute("currentPage", pageNb);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());


        model.addAttribute("epreuves", epreuves);
        return "/epreuve";
    }
}
