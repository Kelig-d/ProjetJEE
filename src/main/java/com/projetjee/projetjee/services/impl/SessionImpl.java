package com.projetjee.projetjee.services.impl;

import com.projetjee.projetjee.entities.Session;
import com.projetjee.projetjee.repository.SessionRepository;
import com.projetjee.projetjee.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Override
    public List<Session.dates> getDatesBySite(String site) {
        return sessionRepository.findAllBySite_Nom(site);
    }

}
