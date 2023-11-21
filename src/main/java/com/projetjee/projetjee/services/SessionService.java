package com.projetjee.projetjee.services;

import com.projetjee.projetjee.entities.Session;
import com.projetjee.projetjee.entities.Site;

import java.util.List;

public interface SessionService {
    List<Session.dates> getDatesBySite(String site);
    List<Session> getAll();

    String getCode(String discipline);

    void save(Session session);
}
