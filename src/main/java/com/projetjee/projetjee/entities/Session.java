package com.projetjee.projetjee.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Session")

public class Session {

    @Id
    private String code;

    @OneToOne
    @JoinColumn(name = "id_site", referencedColumnName = "id_site")
    private Site site;

    @OneToOne
    @JoinColumn(name = "id_epreuve", referencedColumnName = "id_epreuve")
    private Epreuve epreuve;

    private Date date;
    private Time heure_debut;
    private Time heure_fin;
    private String description;

    @OneToOne
    @JoinColumn(name="type_session", referencedColumnName = "type_session")
    private TypeSession type_session;
}
