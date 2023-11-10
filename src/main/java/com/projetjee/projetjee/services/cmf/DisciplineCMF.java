package com.projetjee.projetjee.services.cmf;

import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.repository.DisciplineRepository;
import com.projetjee.projetjee.services.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DisciplineCMF implements DisciplineService{

    @Autowired
    private DisciplineRepository disciplineRepository;

    // Save Discipline
    @Override
    public Discipline saveDiscipline(Discipline discipline)
    {
        return disciplineRepository.save(discipline);
    }

    // Read Discipline
    @Override public List<Discipline> fetchDisciplineList()
    {
        return (List<Discipline>)disciplineRepository.findAll();
    }

    // Update Discipline
    @Override
    public Discipline updateDiscipline(Discipline disciplineOlympique, Long id_discipline)
    {
        Discipline discipline = disciplineRepository.findById(id_discipline).get();

        if (Objects.nonNull(discipline.getNom()) && !"".equalsIgnoreCase(discipline.getNom())) {
            discipline.setNom(discipline.getNom());
        }

        if (Objects.nonNull(discipline.getParalympique()) && !"".equalsIgnoreCase(String.valueOf(discipline.getParalympique()))) {
            discipline.setParalympique(discipline.getParalympique());
        }

        return disciplineRepository.save(discipline);
    }

    // Delete Discipline
    @Override
    public void deleteDisciplineById(Long id_discipline)
    {
        disciplineRepository.deleteById(id_discipline);
    }
}
