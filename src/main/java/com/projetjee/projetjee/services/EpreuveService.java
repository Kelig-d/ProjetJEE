package com.projetjee.projetjee.services;

import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.entities.Epreuve;
import com.projetjee.projetjee.entities.Session;

import java.util.List;

public interface EpreuveService {

    //Read Epreuve
    List<Epreuve> findAll();

    // Update Epreuve
    Epreuve updateEpreuve(Long id_epreuve ,String nom ,Discipline id_discipline);

    // Save Epreuve
    Epreuve saveEpreuve(String nom,Discipline id_discipline);

    // Delete Epreuve by discipline
    void deleteEpreuveById(Long id_epreuve);

    Session getSessionsByEpreuve(Epreuve epreuve);

    List<Epreuve> getEpreuveById(Long id_epreuve);

    List<Epreuve> getEpreuveByDiscipline(Discipline discipline);

}
