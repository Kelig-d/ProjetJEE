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

    @ManyToOne
    @JoinColumn(name = "id_site", referencedColumnName = "id_site")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "id_epreuve", referencedColumnName = "id_epreuve")
    private Epreuve epreuve;

    private Date date;
    private Time heureDebut;
    private Time heureFin;
    private String description;

    @ManyToOne
    @JoinColumn(name="type_session", referencedColumnName = "nom")
    private TypeSession type_session;

    public interface dates {
        Date getDate();
        Time getHeureDebut();
        Time getHeureFin();
    }
}
