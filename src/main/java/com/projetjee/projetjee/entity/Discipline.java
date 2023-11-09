package com.projetjee.projetjee.entity;

// Importing required classes
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_discipline;
    private String nom;
    private Boolean paralympique;


    public Long getId_discipline() {
        return id_discipline;
    }

    public void setId_discipline(Long id_discipline) {
        this.id_discipline = id_discipline;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getParalympique() {
        return paralympique;
    }

    public void setParalympique(Boolean paralympique) {
        this.paralympique = paralympique;
    }

    @Override
    public String toString() {
        return "Discipline [id=" + id_discipline + ", name=" + nom + ", flags=" + paralympique + "]";
    }


}
