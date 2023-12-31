package com.projetjee.projetjee.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Session")

public class Session implements Persistable<Long> {

    @Id
    private String code;

    @ManyToOne
    @JoinColumn(name = "id_site", referencedColumnName = "id_site")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "id_epreuve", referencedColumnName = "id_epreuve")
    private Epreuve epreuve;

    @Column(name = "date_debut")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateDebut;
    @Column(name = "date_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateFin;

    private String description;

    @ManyToOne
    @JoinColumn(name="type_session", referencedColumnName = "nom")
    private TypeSession typeSession;

    @Transient

    private boolean isNew = true;
    @Override
    public Long getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }



    public interface sessionMinimized {
        Date getDateDebut();
        Date getDateFin();
        String getEpreuveDisciplineNom();
        Site.SiteMinimized getSite();

    }
    public interface dates {
        Date getDateDebut();
        Date getDateFin();
    }

    public interface Code {
        String getCode();
    }

    public interface infiniteFix {
        String getCode();
        LocalDateTime getDateDebut();
        LocalDateTime getDateFin();
        String getDescription();
        TypeSession getTypeSession();
        Site getSite();
        Epreuve.EpreuveMinimized getEpreuve();
    }
}
