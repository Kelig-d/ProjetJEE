package com.projetjee.projetjee.services.impl;

import com.projetjee.projetjee.entities.Site;
import com.projetjee.projetjee.repository.SiteRepository;
import com.projetjee.projetjee.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SiteImpl implements SiteService {
    @Autowired
    private SiteRepository siteRepository;

    @Override
    public Map<String,List<String>> getAllGroupByCategorie() {
        return siteRepository.findAllProjectedBy().stream().collect(Collectors.groupingBy(Site.SiteCategory::getCategorieNom, Collectors.mapping(Site.SiteCategory::getNom, Collectors.toList())));

    }

}
