package com.projetjee.projetjee.services.impl;

import com.projetjee.projetjee.entities.Categorie;
import com.projetjee.projetjee.entities.Site;
import com.projetjee.projetjee.repository.SiteRepository;
import com.projetjee.projetjee.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SiteImpl implements SiteService {
    @Autowired
    private SiteRepository siteRepository;

    @Override
    public Map<String,List<String>> getAllGroupByCategorie() {
        return siteRepository.findAllProjectedBy().stream().collect(Collectors.groupingBy(Site.SiteCategory::getCategorieNom, Collectors.mapping(Site.SiteCategory::getNom, Collectors.toList())));

    }

    @Override
    public Site getSiteByNom(String nom) {
        return siteRepository.findFirstByNom(nom);
    }



    @Override
    public Site saveSite(String nom, String ville, Categorie categorie)
    {
        System.out.println("Création du site");
        Site site = new Site();
        site.setNom(nom);
        site.setVille(ville);
        site.setCategorie(categorie);
        System.out.println("Fin création du site");
        return siteRepository.save(site);
    }

    // Read Discipline with epreuve
    @Override
    public List<Site> findAll(){return (List<Site>)siteRepository.findAll();}

    public List<String> getCategories(){
        return (List<String>)siteRepository.getCategories();
    }
    // Update Discipline
    @Override
    public Site updateSite(Long id_site , String nom, String ville, Categorie categorie)
    {
        Site site = siteRepository.findById(id_site).get();

        if (Objects.nonNull(site.getNom()) && !"".equalsIgnoreCase(nom)) {
            site.setNom(nom);
        }

        if (Objects.nonNull(site.getVille()) && !"".equalsIgnoreCase(ville)) {
            site.setVille(ville);
        }

        site.setCategorie(categorie);

        return siteRepository.save(site);
    }

    // Delete Discipline
    @Override
    public void deleteSiteById(Long id_site)
    {

        siteRepository.deleteById(id_site);
    }




}
