package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.services.SiteService;
import com.projetjee.projetjee.entities.Site;
import com.projetjee.projetjee.entities.Categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class SiteController {
    @Autowired private SiteService siteService;

    /**
     * {@code GET /site} : get all the site.
     *
     * @return
     */
    @GetMapping("/site")
    public String showUserList(Model model) {
        model.addAttribute("site", siteService.findAll());
        return "site";
    }
    // Save Discipline
    @PostMapping("/add_site")
    public String saveSite(@RequestParam("nameSite") String name, @RequestParam("nameVille") String ville, @RequestParam("categorie") Categorie categorie)
    {
        siteService.saveSite(name,ville, categorie);
        return "redirect:/site";
    }

    // Update operation
    @PostMapping("/edit_site")
    public String updateSite(@RequestParam("id_site") Long id_site, @RequestParam("nameSite") String name, @RequestParam("nameVille") String ville, @RequestParam("categorie") Categorie categorie)
    {
        siteService.updateSite(id_site, name, ville, categorie);
        return "redirect:/site";
    }

    // Delete operation
    @GetMapping("/delete_site/{id}")
    public String deleteSite(@PathVariable("id") Long id_site)
    {
        siteService.deleteSiteById(id_site);
        return "redirect:/site";
    }
}
