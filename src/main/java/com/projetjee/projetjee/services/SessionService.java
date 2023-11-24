package com.projetjee.projetjee.services;

import com.projetjee.projetjee.entities.Session;
import com.projetjee.projetjee.entities.Site;

import java.util.List;

public interface SessionService {
    List<Session.dates> getDatesBySite(String site);
    List<Session.infiniteFix> getAll();

    String getCode(String discipline);

    void save(Session session);

    void delete(String session);
}
