package com.projetjee.projetjee.repository;

import com.projetjee.projetjee.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
