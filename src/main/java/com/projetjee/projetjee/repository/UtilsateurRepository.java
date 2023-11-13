package com.projetjee.projetjee.repository;

import com.projetjee.projetjee.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilsateurRepository  extends JpaRepository<Utilisateur,Long>{
    List<Utilisateur> findByLoginAndPassword(String login, String password);
}

