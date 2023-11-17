package com.projetjee.projetjee.services;

import com.projetjee.projetjee.entities.Epreuve;

import java.util.List;

public interface EpreuveService {
    // Read Discipline with epreuve
    List<Epreuve> findAllEpreuveByDiscipline(Long id);
}
