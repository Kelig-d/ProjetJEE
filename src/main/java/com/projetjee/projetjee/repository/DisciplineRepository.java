package com.projetjee.projetjee.Repository;

import com.projetjee.projetjee.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface DisciplineRepository extends JpaRepository<Discipline,Long> {

}
