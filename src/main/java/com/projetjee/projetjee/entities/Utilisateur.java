package com.projetjee.projetjee.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Utilisateur")
public class Utilisateur {
    @Id
    private String login;
    private String password;
    @Getter
    private String role;

    public Utilisateur(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
