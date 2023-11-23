package com.projetjee.projetjee.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Discipline")

public class Discipline {

    @Id
    private Long id_discipline;
    private String nom;
    private boolean paralympique;


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

    public boolean isParalympique() {
        return paralympique;
    }

    public void setParalympique(boolean paralympique) {
        this.paralympique = paralympique;
    }

}
