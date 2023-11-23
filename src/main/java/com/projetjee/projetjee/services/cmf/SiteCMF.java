package com.projetjee.projetjee.services.cmf;

import com.projetjee.projetjee.entities.Categorie;
import com.projetjee.projetjee.entities.Site;
import com.projetjee.projetjee.repository.SiteRepository;
import com.projetjee.projetjee.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SiteCMF implements SiteService {

    @Autowired
    private SiteRepository siteRepository;

    // Save Discipline
    @Override
    public Site saveSite(String nom, String ville, Categorie categorie)
    {
        Site site = new Site();
        site.setNom(nom);
        site.setVille(ville);
        site.setCategorie(categorie);
        return siteRepository.save(site);
    }

    // Read Discipline with epreuve
    @Override
    public List<Site> findAll(){return (List<Site>)siteRepository.findAllSite();}

    // Update Discipline
    @Override
    public Site updateSite(Long id_site ,String nom, String ville, Categorie categorie)
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