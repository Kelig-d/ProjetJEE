package com.projetjee.projetjee.services;

import com.projetjee.projetjee.entities.Session;

import java.util.List;

public interface SessionService {
    List<Session.dates> getDatesBySite(String site);
}
