package com.projetjee.projetjee.services;

import com.projetjee.projetjee.entities.Utilisateur;
import org.json.JSONException;

public interface UtilisateurService {

    boolean login(Utilisateur Utilisateur);

    String generateToken(Utilisateur Utilisateur) throws JSONException;
}
