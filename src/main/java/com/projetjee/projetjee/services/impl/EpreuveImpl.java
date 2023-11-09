package com.projetjee.projetjee.services.impl;

import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.entities.Epreuve;
import com.projetjee.projetjee.repository.EpreuveRepository;
import com.projetjee.projetjee.services.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpreuveImpl implements EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;

    @Override
    public List<String> getByDiscipline(String nom){
        return epreuveRepository.findAllByDiscipline_Nom(nom);
    }
}
