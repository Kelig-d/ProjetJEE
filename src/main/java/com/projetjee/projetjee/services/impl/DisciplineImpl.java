package com.projetjee.projetjee.services.cmf;

import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.repository.DisciplineRepository;
import com.projetjee.projetjee.services.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DisciplineImpl implements DisciplineService{

    @Autowired
    private DisciplineRepository disciplineRepository;
    @Override
    public List<String> getAllNames() {
        return disciplineRepository.findAll().stream()
                .map(Discipline::getNom).toList();
    }

    // Save Discipline
    @Override
    public Discipline saveDiscipline(String nom, Boolean paralympique)
    {
        Discipline discipline = new Discipline();
        discipline.setNom(nom);
        discipline.setParalympique(paralympique);
        return disciplineRepository.save(discipline);
    }

    // Read Discipline with epreuve
    @Override
    public List<Discipline> findAll(){return (List<Discipline>)disciplineRepository.findAllEpreuveDiscipline();}

    // Update Discipline
    @Override
    public Discipline updateDiscipline(Long id_discipline ,String nom, Boolean paralympique)
    {
        Discipline discipline = disciplineRepository.findById(id_discipline).get();

        if (Objects.nonNull(discipline.getNom()) && !"".equalsIgnoreCase(nom)) {
            discipline.setNom(nom);
        }

        discipline.setParalympique(paralympique);

        return disciplineRepository.save(discipline);
    }

    // Delete Discipline
    @Override
    public void deleteDisciplineById(Long id_discipline)
    {

        disciplineRepository.deleteById(id_discipline);
    }

    @Override
    public List<Discipline> getAll() {
        return disciplineRepository.findAll();
    }
}


