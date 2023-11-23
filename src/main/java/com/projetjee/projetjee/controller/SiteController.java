package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.entities.*;
import com.projetjee.projetjee.repository.SiteRepository;
import com.projetjee.projetjee.services.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class SiteController {
    @Autowired private SiteService siteService;
    //@Autowired private CategorieService categorieService;

    /**
     * {@code GET /site} : get all the site.
     *
     * @return
     */
    @GetMapping("/site")
    public String showSite(Model model) {
        model.addAttribute("site", siteService.findAll());
        return "site";
    }
    // Save Discipline
    @PostMapping("/add_site")
    public String saveSite(@RequestParam("nameSite") String name, @RequestParam("nameVille") String ville, @RequestParam("categorie") String categorie)
    {
        Categorie cat = siteService.getByNom(categorie);
        System.out.println("Sauvegarde du site");
        siteService.saveSite(name,ville, cat);
        return "redirect:/site";
    }

    // Update operation
    @PostMapping("/edit_site")
    public String updateSite(@RequestParam("id_site") Long id_site, @RequestParam("nameSite") String name, @RequestParam("nameVille") String ville, @RequestParam("categorie") String categorie)
    {
        Categorie cat = siteService.getByNom(categorie);
        if(cat != null){
            siteService.updateSite(id_site, name, ville, cat);
        }
        return "redirect:/site";
    }

    // Delete operation
    @GetMapping("/delete_site/{id}")
    public String deleteSite(@PathVariable("id") Long id_site)
    {
        siteService.deleteSiteById(id_site);
        return "redirect:/site";
    }

    @ResponseBody
    @GetMapping("/createSessionCategorie")
    public ResponseEntity<JSONObject> sendCreateSessionCategorie() {
        JSONObject response = new JSONObject();
        response.put("categories", siteService.getCategories());
        return ResponseEntity.ok(response);
    }
}
