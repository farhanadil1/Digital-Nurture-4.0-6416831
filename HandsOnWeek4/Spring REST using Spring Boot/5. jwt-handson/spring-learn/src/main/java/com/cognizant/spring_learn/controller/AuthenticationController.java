package com.cognizant.spring_learn.controller;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        LOGGER.info("START /authenticate");
        LOGGER.debug("Authorization Header: {}", authHeader);

        String username = getUser(authHeader);
        LOGGER.debug("Extracted Username: {}", username);

        Map<String, String> map = new HashMap<>();
        if ("user".equals(username)) {
            String token = generateJwt(username);
            map.put("token", token);
        } else {
            map.put("token", "");
        }

        LOGGER.info("END /authenticate");
        return ResponseEntity.ok(map);
    }

    private String getUser(String authHeader) {
        LOGGER.info("Inside getUser()");
        try {
            String base64Credentials = authHeader.substring("Basic".length()).trim();
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String decodedString = new String(decodedBytes);
            LOGGER.debug("Decoded Credentials: {}", decodedString);
            String[] credentials = decodedString.split(":", 2);
            return credentials[0]; 
        } catch (Exception e) {
            LOGGER.error("Error decoding Authorization header", e);
            return null;
        }
    }

    private String generateJwt(String user) {
        LOGGER.info("Inside generateJwt() for user: {}", user);
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user);
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date((new Date()).getTime() + 1200000)); 
        builder.signWith(SignatureAlgorithm.HS256, "secretkey");

        String token = builder.compact();
        LOGGER.debug("Generated JWT: {}", token);
        return token;
    }
}
