package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.entities.*;
import com.projetjee.projetjee.services.*;
import lombok.val;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.projetjee.projetjee.services.securityUtils.verifToken;


@Controller
public class DisciplineController {

    @Autowired private DisciplineService disciplineService;
    @Autowired private EpreuveService epreuveService;

    // Read all discipline
    @GetMapping("/discipline")
    public String showDisciplineList(@CookieValue(value="JWebToken", required=false) String bearerToken, Model model) {
        if (bearerToken != null) {
            if (verifToken(bearerToken, "administratif")){

                return findPaginated(1,  model);
            }
            else return "index";
        }
        return "login";

    }

    @GetMapping("/discipline/{pageNb}")
    public String findPaginated(@PathVariable(value="pageNb") int pageNb, Model model ){
        int pageSize = 10;
        Page<Discipline> page = disciplineService.findPaginated(pageNb,pageSize);
        List<Discipline> disciplines = page.getContent();
        model.addAttribute("currentPage", pageNb);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());


        model.addAttribute("disciplines", disciplines);
        return "/discipline";
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
        return "redirect:/discipline";
    }
}
