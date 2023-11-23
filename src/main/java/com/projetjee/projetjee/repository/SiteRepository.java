package com.projetjee.projetjee.repository;

import com.projetjee.projetjee.entities.Site;
import com.projetjee.projetjee.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long>
{
    @Query(value=" SELECT * "+
            "FROM site",
            nativeQuery = true)
    List<Site> findAllSite();


    // Bon résultat
    @Query(value=" SELECT DISTINCT * "+
            "FROM categorie",
            nativeQuery = true)
    List<String> getCategories();

    @Query(value=" SELECT DISTINCT nom "+
            "FROM categorie "+
            "WHERE nom = :name ",
            nativeQuery = true)
    Categorie findCategorie(@Param("name") String nom);



}
