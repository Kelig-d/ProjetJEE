package com.projetjee.projetjee.Services;

import com.projetjee.projetjee.entity.Discipline;

import java.util.List;

public interface DisciplineService {

    // Save Discipline
    Discipline saveDiscipline(Discipline discipline);

    // Read Discipline
    List<Discipline> fetchDisciplineList();

    // Update Discipline
    Discipline updateDiscipline(Discipline discipline, Long id_discipline);

    // Delete Discipline
    void deleteDisciplineById(Long id_discipline);
}
