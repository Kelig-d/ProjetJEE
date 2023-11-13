package com.projetjee.projetjee.repository;

import com.projetjee.projetjee.entities.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {
    List<Epreuve.Nom> findAllByDiscipline_Nom(String discipline);
}
