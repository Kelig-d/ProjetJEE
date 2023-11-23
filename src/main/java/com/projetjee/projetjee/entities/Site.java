package com.projetjee.projetjee.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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
    @Getter
    private String nom;
    private String ville;

    @ManyToOne
    @JoinColumn(name = "Categorie", referencedColumnName = "nom")

    private Categorie categorie;


    public interface SiteCategory {
        String getNom();
        String getCategorieNom();
    }

    public interface SiteMinimized {
        String getNom();
        String getVille();
    }

}
