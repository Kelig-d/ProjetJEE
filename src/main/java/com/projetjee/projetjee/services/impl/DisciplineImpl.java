package com.projetjee.projetjee.services.impl;

import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.repository.DisciplineRepository;
import com.projetjee.projetjee.services.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineImpl implements DisciplineService{

    @Autowired
    private DisciplineRepository disciplineRepository;
    @Override
    public List<String> getAllNames() {
        return disciplineRepository.findAll().stream()
                .map(Discipline::getNom).toList();
    }

}
