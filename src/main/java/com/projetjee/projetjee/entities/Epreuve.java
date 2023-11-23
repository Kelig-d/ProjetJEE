package com.projetjee.projetjee.entities;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Epreuve")

public class Epreuve {

    @Id
    private Long id_epreuve;

    @ManyToOne
    @JoinColumn(name = "id_discipline", referencedColumnName = "id_discipline")
    private Discipline discipline;

    @Getter
    private String nom;

    public interface Nom{
        String getNom();
    }
}
