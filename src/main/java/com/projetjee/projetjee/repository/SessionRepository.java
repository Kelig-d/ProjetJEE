package com.projetjee.projetjee.repository;

import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.entities.Epreuve;
import com.projetjee.projetjee.entities.Session;
import com.projetjee.projetjee.entities.Site;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    <T>List<T> findAllBySite_Nom(String site, Class<T> tClass);
    Session.Code findFirstByEpreuve_Discipline_NomOrderByCodeDesc(String dis);

    @Transactional
    void deleteByCode(String code);
    Session getFirstByEpreuve(Epreuve epreuve);

    List<Session.infiniteFix> findAllProjectedBy();
}
