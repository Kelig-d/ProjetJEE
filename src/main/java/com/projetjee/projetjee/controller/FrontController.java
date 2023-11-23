package com.projetjee.projetjee.controller;

import com.projetjee.projetjee.entities.JWebToken;
import com.projetjee.projetjee.entities.Utilisateur;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static com.projetjee.projetjee.services.securityUtils.verifToken;

@Controller
public class FrontController {




    @GetMapping("/index")
    public String homePage(@CookieValue(value="JWebToken", required=false) String bearerToken){
        if (bearerToken != null) {
            if (verifToken(bearerToken, "")) return "index";
        }
        return "login";
    }

    @GetMapping("/discipline")
    public String disciplinePage(@CookieValue(value="JWebToken", required=false)String bearerToken){
        if (bearerToken != null) {
            if (verifToken(bearerToken, "administratif")){

                return "discipline";
            }
            else return "/index";
        }
        return "login";
    }

    @GetMapping("/session")
    public String sessionPage(@CookieValue(value="JWebToken", required=false) String bearerToken){
        if (bearerToken != null) {
            if (verifToken(bearerToken, "session")){

                return "session";
            }
            else return "/index";
        }
        return "login";
    }

    @Autowired
    private com.projetjee.projetjee.services.impl.UtilisateurImpl UtilisateurImpl;

    @Autowired
    private com.projetjee.projetjee.repository.UtilsateurRepository UtilsateurRepository;

    @GetMapping({"/", "/login"})
    public String greetingForm(@CookieValue(value="JWebToken", required=false) String bearerToken) {
        if (bearerToken != null) {
            if (verifToken(bearerToken, "")) return "index";
        }
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestBody MultiValueMap<String,String> formData, HttpServletResponse response) throws JSONException {
        Utilisateur Utilisateur = new Utilisateur(formData.getFirst("login"),formData.getFirst("password"));
        boolean empty= UtilisateurImpl.login(Utilisateur);
        if(empty){
            return new ModelAndView("redirect:/login");
        }else{
            Utilisateur UtilisationVerify = UtilsateurRepository.findByLoginAndPassword(Utilisateur.getLogin(),Utilisateur.getPassword()).getFirst();
            String Token = UtilisateurImpl.generateToken(UtilisationVerify);
            final Cookie JWebToken = new Cookie("JWebToken",Token);
            JWebToken.setMaxAge(999999);
            JWebToken.setPath("/");
            JWebToken.setDomain("localhost");
            ResponseEntity<String> Response = new ResponseEntity<>("authorized", HttpStatus.OK);
            response.addCookie(JWebToken);
            return new ModelAndView("redirect:/index");
        }
    }




    @GetMapping("/logout")
    public ModelAndView logout(HttpServletResponse response) {
        final Cookie JWebToken = new Cookie("JWebToken", "");
        JWebToken.setMaxAge(0);
        JWebToken.setHttpOnly(true);
        JWebToken.setPath("/");
        JWebToken.setDomain("localhost");
        ResponseEntity<String> Response = new ResponseEntity<>("logged out", HttpStatus.OK);
        response.addCookie(JWebToken);
        return new ModelAndView("redirect:/login");
    }


}
