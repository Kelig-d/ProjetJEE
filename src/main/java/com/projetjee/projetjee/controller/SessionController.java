package com.projetjee.projetjee.controller;


import com.projetjee.projetjee.entities.Epreuve;
import com.projetjee.projetjee.services.DisciplineService;
import com.projetjee.projetjee.services.EpreuveService;
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
        response.put("epreuves", epreuveService.getByDiscipline(dis));
        return ResponseEntity.ok(response);
    }
}
