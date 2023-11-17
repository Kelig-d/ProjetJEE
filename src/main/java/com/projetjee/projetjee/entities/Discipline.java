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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_discipline;
    private String nom;
    private Boolean paralympique;

    @Column(name = "name_epreuve",insertable = false,updatable = false)
    private String name_epreuve;

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
