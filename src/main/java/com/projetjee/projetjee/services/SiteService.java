package com.projetjee.projetjee.services;

import com.projetjee.projetjee.entities.Site;

import java.util.List;
import java.util.Map;

public interface SiteService {
    Map<String,List<String>> getAllGroupByCategorie();
    Site getSiteByNom(String nom);

}
