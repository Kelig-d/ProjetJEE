package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.entities.JWebToken;
import com.projetjee.projetjee.entities.Utilisateur;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.NoSuchAlgorithmException;

@Controller
public class FrontController {


    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/session")
    public String sessionPage(){
        return "session";
    }

    @GetMapping("/site")
    public String sitePage(){
        return "site";
    }

    @Autowired
    private com.projetjee.projetjee.services.impl.UtilisateurImpl UtilisateurImpl;

    @Autowired
    private com.projetjee.projetjee.repository.UtilsateurRepository UtilsateurRepository;
    @GetMapping("/login")
    public String greetingForm(Model model) {
        System.out.println("Step 1");
        //model.addAttribute("logStat", new Connexion());
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MultiValueMap<String,String> formData, HttpServletResponse response) throws JSONException {
        System.out.println("Step 2");
        Utilisateur Utilisateur = new Utilisateur(formData.getFirst("login"),formData.getFirst("password"));
        boolean empty= UtilisateurImpl.login(Utilisateur);
        if(empty){
            return new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED);
        }else{
            Utilisateur UtilisationVerify = UtilsateurRepository.findByLoginAndPassword(Utilisateur.getLogin(),Utilisateur.getPassword()).getFirst();
            String Token = UtilisateurImpl.generateToken(UtilisationVerify);
            final Cookie JWebToken = new Cookie("JWebToken",Token);
            JWebToken.setMaxAge(999999);
            JWebToken.setPath("/");
            JWebToken.setDomain("localhost");
            ResponseEntity<String> Response = new ResponseEntity<>("authorized", HttpStatus.OK);
            response.addCookie(JWebToken);
            return Response;
        }
    }
    @PostMapping("/token")
    public ResponseEntity<String> verifToken(@CookieValue("JWebToken") String bearerToken, HttpServletResponse response) throws NoSuchAlgorithmException, JSONException {
        JWebToken incomingToken = null;
        try {
            incomingToken = new JWebToken(bearerToken);
        } catch (NoSuchAlgorithmException e) {
            return new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED);
        }
        if (!incomingToken.isValid()) {
            //suppresion du token
            return new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED);
        }
        else {
            String privilege = incomingToken.getRole();
            return new ResponseEntity<>(privilege, HttpStatus.OK);
        }
    }



    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        final Cookie JWebToken = new Cookie("JWebToken", "");
        JWebToken.setMaxAge(0);
        JWebToken.setHttpOnly(true);
        JWebToken.setPath("/");
        JWebToken.setDomain("localhost");
        ResponseEntity<String> Response = new ResponseEntity<>("logged out", HttpStatus.OK);
        response.addCookie(JWebToken);
        return Response;
    }

    @GetMapping("/test1")
    public String test(){
        return "test";
    }

    @GetMapping("/discipline")
    public String disciplinePage(){
        return "discipline";
    }

}
