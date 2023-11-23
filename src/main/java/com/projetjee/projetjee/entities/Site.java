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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_site;
    private String nom;
    private String ville;
    @OneToOne
    @JoinColumn(name = "categorie", referencedColumnName = "nom")
    private Categorie categorie;
}
