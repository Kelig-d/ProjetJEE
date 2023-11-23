package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurService UtilisateurService;

}
