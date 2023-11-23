package com.projetjee.projetjee.services;

import com.projetjee.projetjee.entities.JWebToken;

import java.util.Objects;

public class securityUtils {
    public static Boolean verifToken(String bearerToken, String role)  {
        JWebToken incomingToken = null;
        try {
            incomingToken = new JWebToken(bearerToken);

            if (!incomingToken.isValid()) {
                //suppresion du token
                return false;
            }
            else {
                if(role.isEmpty() || Objects.equals(incomingToken.getRole(), "admin")) return true;
                String privilege = incomingToken.getRole();
                if (privilege.equals(role)) return true;
                else return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
