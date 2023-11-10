package com.projetjee.projetjee.repository;
import com.projetjee.projetjee.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface DisciplineRepository extends JpaRepository<Discipline,Long> {

}
