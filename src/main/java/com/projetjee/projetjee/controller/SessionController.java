package com.projetjee.projetjee.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.projetjee.projetjee.entities.*;
import com.projetjee.projetjee.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import net.minidev.json.JSONObject;


import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

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
    @Autowired
    private TypeSessionService typeSessionService;



    @ResponseBody
    @GetMapping("/createSessionDisciplines")
    public ResponseEntity<JSONObject> sendCreateSessionDisciplines() {
        JSONObject response = new JSONObject();
        response.put("disciplines", DisciplineService.getAllNames());
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/createSessionEpreuves/{discipline}")
    public ResponseEntity<JSONObject> sendCreateSessionEpreuves(@PathVariable("discipline") String dis) {
        JSONObject response = new JSONObject();
        response.put("epreuves", epreuveService.getByDiscipline(dis));
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
        response.put("dates", mapper.writeValueAsString(sessionService.getDatesBySite(site)));
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping("/getTimesByDate")
    public ResponseEntity<JSONObject> sendCreateSessionTimes(@RequestBody LinkedHashMap<String, Object> session) {
        JSONObject response = new JSONObject();
        if (session.get("dateFin") == null) {
            List<String> bannedHours = new ArrayList<>();
            List<String> heureDebuts = new ArrayList<>();
            List<Session.infiniteFix> sessions = sessionService.getAll();
            for (Session.infiniteFix s : sessions) {
                if (!(Timestamp.valueOf(s.getDateFin()).getTime() < (long) (session.get("dateDebut")) || Timestamp.valueOf(s.getDateDebut()).getTime() > (long) (session.get("dateDebut")) + 82800000)) {
                    LinkedHashMap<String, String> hm = (LinkedHashMap<String, String>) (session.get("site"));
                    String siteName =  hm.get("nom");

                    int firstHour = s.getDateDebut().getHour();
                    int lastHour = 0;
                    if (Objects.equals(s.getEpreuve().getDiscipline().getNom(),session.get("epreuveDisciplineNom"))) lastHour = s.getDateDebut().getHour();
                    if (Objects.equals(s.getSite().getNom(), siteName)){
                        lastHour = s.getDateFin().getHour()+1;
                        firstHour--;
                    }

                    for(int i=firstHour;i<lastHour;i++){
                        bannedHours.add(i<10?"0"+i+":00":i+":00");
                    }
                }
            }
            for (int i = 0;i<24;i++){
                heureDebuts.add(i<10?"0"+i+":00":i+":00");
            }
            heureDebuts.removeAll(bannedHours);
            response.put("heureDebuts", heureDebuts);
        } else {
            List<String> HeureFins = new ArrayList<>();
            List<Session.infiniteFix> sessions = sessionService.getAll();
            int limit = 24;
            for (Session.infiniteFix s : sessions) {
                LocalDateTime selDate = new Timestamp((long)session.get("dateDebut")).toLocalDateTime();
                if(s.getDateDebut().getDayOfMonth() == selDate.getDayOfMonth() && s.getDateDebut().getMonthValue() == selDate.getMonthValue() && s.getDateDebut().getYear() == selDate.getYear() && s.getDateDebut().getHour() > selDate.getHour()){
                    if(Objects.equals(s.getSite().getNom(), ((LinkedHashMap<String, String>) (session.get("site"))).get("nom"))){
                        if(limit>s.getDateDebut().getHour()+1) limit = s.getDateDebut().getHour();
                    }
                    else{
                        if(limit>s.getDateDebut().getHour() && s.getEpreuve().getDiscipline().getNom() == session.get("epreuveDisciplineNom")) limit = s.getDateDebut().getHour();
                    }
                }
            }
            Timestamp dateDeb = new Timestamp((long)session.get("dateDebut"));
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateDeb);
            for (int i = cal.get(Calendar.HOUR_OF_DAY)+1 ; i < limit; i++) {
                HeureFins.add(i < 10 ? "0" + i + ":00" : i + ":00");
            }
            if(HeureFins.lastIndexOf("23:00")!=-1)  HeureFins.add("00:00");
            response.put("heureFins", HeureFins);
        }
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/getTypeSessions")
    public ResponseEntity<JSONObject> sendCreateSessionTypeSessions() {
        JSONObject response = new JSONObject();
        response.put("typeSessions", typeSessionService.getAll());
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping("/createSession")
    public ResponseEntity<String> createSession(@RequestBody MultiValueMap<String, String> session){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = dateFormat.parse(session.getFirst("startDate") + " " + session.getFirst("startTime") + ":00");
            Timestamp dateDebut = new Timestamp(date.getTime());
            date = dateFormat.parse(session.getFirst("startDate") + " " + session.getFirst("endTime") + ":00");
            Timestamp dateFin = new Timestamp(date.getTime());
            Site site = siteService.getSiteByNom(session.getFirst("site"));
            Epreuve ep = epreuveService.getByDisciplineAndNom(session.getFirst("discipline"), session.getFirst("epreuve"));
            Session s = new Session();
            s.setEpreuve(ep);
            s.setSite(site);
            s.setDateDebut(dateDebut.toLocalDateTime());
            s.setDateFin(dateFin.toLocalDateTime());
            s.setDescription(session.getFirst("desc"));
            s.setTypeSession(new TypeSession(session.getFirst("typeSession")));
            if (session.getFirst("code") != null && session.getFirst("code") != "") {
                s.setCode(session.getFirst("code"));
                s.setIsNew(false);
            }
            else s.setCode(sessionService.getCode(session.getFirst("discipline")));
            sessionService.save(s);
            return new ResponseEntity<>("Session créée", HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("Une erreur est survenue", HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @GetMapping("/getSessions")
    public ResponseEntity<JSONObject> getSessions() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        JSONObject response = new JSONObject();
        var debug = sessionService.getAll();
        response.put("sessions", mapper.writeValueAsString(sessionService.getAll()));
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping("/deleteSession")
    public ResponseEntity<String> deleteSession(@RequestBody LinkedHashMap<String, String> session){
        try {
            sessionService.delete(session.get("code"));
            return new ResponseEntity<>("Session supprimée", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Une erreur est survenue", HttpStatus.BAD_REQUEST);
        }
    }
}
