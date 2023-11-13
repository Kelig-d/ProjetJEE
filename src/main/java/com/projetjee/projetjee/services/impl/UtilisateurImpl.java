package com.projetjee.projetjee.services.impl;

import com.projetjee.projetjee.entities.JWebToken;
import com.projetjee.projetjee.entities.Utilisateur;
import com.projetjee.projetjee.services.UtilisateurService;
import com.projetjee.projetjee.repository.UtilsateurRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Service
public class UtilisateurImpl implements UtilisateurService {

    private static final int EXPIRATIONTIME = 9999999;

    @Autowired
    private UtilsateurRepository UtilsateurRepository;

    public boolean login(Utilisateur Utilisateur) {
        return UtilsateurRepository.findByLoginAndPassword(Utilisateur.getLogin(),Utilisateur.getPassword()).isEmpty();
    }

    @Override
    public String generateToken(Utilisateur Utilisateur) throws JSONException {

        JSONObject jwtpayload = new JSONObject();
        jwtpayload.put("Etat","connecter");
        jwtpayload.put("sub",Utilisateur.getLogin());
        jwtpayload.put("role",Utilisateur.getRole());

        JSONArray tps = new JSONArray();
        tps.put("Utilisateur");
        jwtpayload.put("tps",tps);

        LocalDateTime ldt = LocalDateTime.now().plus(EXPIRATIONTIME, ChronoUnit.MILLIS);

        jwtpayload.put("exp",ldt.toEpochSecond(ZoneOffset.UTC));

        return new JWebToken(jwtpayload).toString();
    }
}
