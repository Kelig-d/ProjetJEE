package com.projetjee.projetjee.services;

import com.projetjee.projetjee.entities.Categorie;
import com.projetjee.projetjee.entities.Site;

import java.util.List;


public interface SiteService {

    // Save Site
    Site saveSite(String nom, String ville, Categorie categorie);

    //
    List<Site> findAll();
    // Update Site
    Site updateSite(Long id_site, String nom, String ville, Categorie categorie);
    // Delete Discipline
    void deleteSiteById(Long id_site);
}
