package com.projetjee.projetjee.services;

import com.projetjee.projetjee.entities.Epreuve;

import java.util.List;

public interface EpreuveService {
    List<String> getByDiscipline(String discipline);

    Epreuve getByDisciplineAndNom(String discipline, String nom);
}
