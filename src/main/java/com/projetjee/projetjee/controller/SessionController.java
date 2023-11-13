package com.projetjee.projetjee.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetjee.projetjee.entities.Epreuve;
import com.projetjee.projetjee.entities.Site;
import com.projetjee.projetjee.services.DisciplineService;
import com.projetjee.projetjee.services.EpreuveService;
import com.projetjee.projetjee.services.SessionService;
import com.projetjee.projetjee.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.minidev.json.JSONObject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SessionController {
    @Autowired
    private DisciplineService DisciplineService;
    @Autowired
    private EpreuveService epreuveService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private SessionService sessionService;

    @ResponseBody
    @GetMapping("/createSessionDisciplines")
    public ResponseEntity<JSONObject> sendCreateSessionDisciplines(){
        JSONObject response = new JSONObject();
        response.put("disciplines", DisciplineService.getAllNames());
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/createSessionEpreuves/{discipline}")
    public ResponseEntity<JSONObject> sendCreateSessionEpreuves(@PathVariable("discipline") String dis){
        JSONObject response = new JSONObject();
        response.put("epreuves", epreuveService.getByDiscipline(dis) );
        return ResponseEntity.ok(response);
    }


    @ResponseBody
    @GetMapping("/createSessionSites")
    public ResponseEntity<JSONObject> sendCreateSessionSites() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject response = new JSONObject();
        response.put("sites", mapper.writeValueAsString(siteService.getAllGroupByCategorie()));
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/getDates/{site}")
    public ResponseEntity<JSONObject> sendCreateSessionDate(@PathVariable("site") String site) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject response = new JSONObject();
        response.put("dates",mapper.writeValueAsString(sessionService.getDatesBySite(site)));
        return ResponseEntity.ok(response);
    }
}
