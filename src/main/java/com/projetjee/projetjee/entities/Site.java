package com.projetjee.projetjee.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Site")

public class Site {

    @Id
    private Long id_site;
    private String nom;
    private String ville;
    @ManyToOne
    @JoinColumn(name = "Categorie", referencedColumnName = "nom")
    private Categorie categorie;
}