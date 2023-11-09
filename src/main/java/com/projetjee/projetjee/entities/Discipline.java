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
    private Long id;
    private String nom;
    private boolean paralympique;
}
