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
    private Long id_epreuve;

    @ManyToOne
    @JoinColumn(name = "id_discipline", referencedColumnName = "id_discipline")
    private Discipline discipline;

    private String nom;
}
