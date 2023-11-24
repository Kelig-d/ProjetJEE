package com.projetjee.projetjee.services;

import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.entities.Epreuve;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DisciplineService {

    // Save Discipline
    Discipline saveDiscipline(String nom,Boolean paralympique);


    //Read Discipline
    List<Discipline> findAll();

    // Read Discipline with epreuve
    List<Discipline> findAllEpreuveDiscipline();

    // Update Discipline
    Discipline updateDiscipline(Long id_discipline ,String nom ,Boolean paralympique);

    // Delete Discipline
    void deleteDisciplineById(Long id_discipline);

    Discipline findDisciplineByNom(String nom);

    List<String> getAllNames();

    Discipline getByNom(String nom);

    Discipline getDisciplineById(Long id_discipline);

    Page<Discipline> findPaginated(int pageNb, int pageSize);

    Page<Discipline> findPaginatedDsicEpr(int pageNb, int pageSize);


    List<Discipline> getAll();
}
