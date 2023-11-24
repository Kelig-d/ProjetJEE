package com.projetjee.projetjee.services.impl;

import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.entities.Epreuve;
import com.projetjee.projetjee.entities.Session;
import com.projetjee.projetjee.repository.EpreuveRepository;
import com.projetjee.projetjee.repository.SessionRepository;
import com.projetjee.projetjee.services.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EpreuveImpl implements EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public List<String> getByDiscipline(String nom){
        return epreuveRepository.findAllByDiscipline_Nom(nom).stream()
                .map(Epreuve.Nom::getNom).toList();
    }

    @Override
    public Epreuve getByDisciplineAndNom(String discipline, String nom) {
        return epreuveRepository.findFirstByDiscipline_NomAndNom(discipline, nom);
    }

    @Override
    public List<Epreuve> findAllEpreuveByDiscipline(Long id) {
        return null;
    }

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

    @Override
    public Session getSessionsByEpreuve(Epreuve epreuve) {
        return sessionRepository.getFirstByEpreuve( epreuve);
    }
    @Override
    public  List<Epreuve> getEpreuveById(Long id_epreuve) {
        return epreuveRepository.findEpreuveById_epreuve(id_epreuve);
    }
    @Override
    public List<Epreuve> getEpreuveByDiscipline(Discipline discipline) {
        return epreuveRepository.getEpreuveByDiscipline(discipline);
    }

    @Override
    public Page<Epreuve> findPaginated(int pageNb, int pageSize) {
        Pageable pageable = PageRequest.of(pageNb-1,pageSize);
        return this.epreuveRepository.findAll(pageable);
    }
}
