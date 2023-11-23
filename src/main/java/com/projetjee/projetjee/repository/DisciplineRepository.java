package com.projetjee.projetjee.repository;
import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.entities.Epreuve;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long>, JpaSpecificationExecutor<Discipline>
{
    @Query(value=" SELECT discipline.id_discipline,discipline.nom, discipline.paralympique,"+
            "GROUP_CONCAT(DISTINCT epreuve.nom ORDER BY epreuve.nom SEPARATOR  ', ' )AS 'name_epreuve' "+
            "FROM discipline,epreuve "+
            "WHERE discipline.id_discipline=epreuve.id_discipline "+
            "GROUP BY discipline.id_discipline",
            nativeQuery = true)
    Page<Discipline> findAllEpreuveDiscipline(Pageable pageable);

    Discipline findFirstByNom(String nom);

    @Query(value="SELECT distinct * "+
            "FROM discipline "+
            "WHERE id_discipline = :id",nativeQuery = true)
    Discipline getDisciplineById(@Param("id") Long id_discipline);

}
