package com.projetjee.projetjee.services.impl;

import com.projetjee.projetjee.entities.Session;
import com.projetjee.projetjee.entities.Site;
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
        return sessionRepository.findAllBySite_Nom(site, Session.dates.class);
    }

    @Override
    public List<Session.infiniteFix> getAll(){
        return sessionRepository.findAllProjectedBy();
    }

    @Override
    public String getCode(String discipline) {
        try {
            String lc = sessionRepository.findFirstByEpreuve_Discipline_NomOrderByCodeDesc(discipline).getCode().substring(3);
            String i = Integer.parseInt(lc) > 9 ? ""+(Integer.parseInt(lc)+1) : "0"+(Integer.parseInt(lc)+1);
            return discipline.substring(0,3) + i;
        }
        catch (Exception e){
            return discipline.substring(0,3) + "01";
        }

    }

    @Override
    public void save(Session session) {
        sessionRepository.saveAndFlush(session);
    }

    @Override
    public void delete(String session) {
        sessionRepository.deleteByCode(session);
    }

}
