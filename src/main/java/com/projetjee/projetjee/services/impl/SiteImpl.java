package com.projetjee.projetjee.services.impl;

import com.projetjee.projetjee.entities.Site;
import com.projetjee.projetjee.repository.SiteRepository;
import com.projetjee.projetjee.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteImpl implements SiteService {
    @Autowired
    private SiteRepository siteRepository;

    @Override
    public List<Site.SiteCategory> getAllNames() {
        return siteRepository.findAllProjectedBy();

    }

}
