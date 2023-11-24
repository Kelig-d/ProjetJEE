package com.projetjee.projetjee.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Epreuve")

public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_epreuve;

    @ManyToOne
    @JoinColumn(name = "id_discipline", referencedColumnName = "id_discipline")
    private Discipline discipline;

    private String nom;

    public Long getId_epreuve() {
        return id_epreuve;
    }

    public void setId_epreuve(Long id_epreuve) {
        this.id_epreuve = id_epreuve;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Discipline getDiscipline(){ return discipline;}

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public interface Nom{
        String getNom();
    }

    public interface EpreuveMinimized{
        Long getId_epreuve();
        String getNom();

        Discipline.DisciplineMinimized getDiscipline();
    }
}
