package com.projetjee.projetjee.repository;

import com.projetjee.projetjee.entities.Epreuve;
import com.projetjee.projetjee.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session.dates> findAllBySite_Nom(String site);
}
