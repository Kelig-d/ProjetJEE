package com.projetjee.projetjee.services.cmf;

import com.projetjee.projetjee.entities.*;
import com.projetjee.projetjee.repository.*;
import com.projetjee.projetjee.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EpreuveCMF implements EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;

    @Override
    public List<Epreuve> findAllEpreuveByDiscipline(Long id){
        return (List<Epreuve>)epreuveRepository.findAllEpreuveByIdDiscipline(id);
    }

    @Override
    public List<String> getByDiscipline(String nom){
        return epreuveRepository.findAllByDiscipline_Nom(nom).stream()
                .map(Epreuve.Nom::getNom).toList();
    }

    // Read Epreuve
    @Override
    public List<Epreuve> findAll(){
        return (List<Epreuve>)epreuveRepository.findAll();
    }

    // Save Epreuve
    @Override
    public Epreuve saveEpreuve(String nom, Discipline id_discipline)
    {
        Epreuve epreuve = new Epreuve();
        epreuve.setNom(nom);
        epreuve.setDiscipline(id_discipline);
        return epreuveRepository.save(epreuve);
    }

    // Update Epreuve
    @Override
    public Epreuve updateEpreuve(Long id_epreuve ,String nom, Discipline id_discipline)
    {
        Epreuve epreuve = epreuveRepository.findById(id_epreuve).get();

        if (Objects.nonNull(epreuve.getNom()) && !"".equalsIgnoreCase(nom)) {
            epreuve.setNom(nom);
        }

        epreuve.setDiscipline(id_discipline);

        return epreuveRepository.save(epreuve);
    }

    // Delete epreuve by discipline
    @Override
    public void deleteEpreuveById(Long id_discipline)
    {
        epreuveRepository.deleteById(id_discipline);
    }

}
