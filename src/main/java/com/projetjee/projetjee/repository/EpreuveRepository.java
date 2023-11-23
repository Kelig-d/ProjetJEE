package com.projetjee.projetjee.repository;

import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.entities.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EpreuveRepository extends JpaRepository<Epreuve, Long> ,JpaSpecificationExecutor<Epreuve>{
    @Query(value="SELECT distinct * "+
            "FROM epreuve "+
            "WHERE id_discipline = :id",nativeQuery = true)
    List<Epreuve> findAllEpreuveByIdDiscipline(@Param("id") Long id_discipline);

    List<Epreuve.Nom> findAllByDiscipline_Nom(String discipline);

}
