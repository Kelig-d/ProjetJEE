package com.projetjee.projetjee.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @Transient
    private String name_epreuve;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Epreuve> epreuves;

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

    public interface DisciplineMinimized{
        Long getId_discipline();
        String getNom();
        Boolean getParalympique();
    }
}
