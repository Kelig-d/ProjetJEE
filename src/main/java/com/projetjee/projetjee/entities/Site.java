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
    private Long id_site;
    @Getter
    private String nom;
    private String ville;

    @ManyToOne
    @JoinColumn(name = "Categorie", referencedColumnName = "nom")
    @Getter
    private Categorie categorie;

    public interface SiteCategory {
        String getNom();
        String getCategorie();
    }

}
