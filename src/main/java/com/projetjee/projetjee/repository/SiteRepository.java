package com.projetjee.projetjee.repository;

import com.projetjee.projetjee.entities.Site;
import com.projetjee.projetjee.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long>
{


    // Bon résultat
    @Query(value=" SELECT DISTINCT * "+
            "FROM categorie",
            nativeQuery = true)
    List<String> getCategories();


    List<Site.SiteCategory> findAllProjectedBy();
    Site findFirstByNom(String nom);
}
