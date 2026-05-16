package com.themaj.smart_books.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}") //this tells spring read value from application.properties
    private String secretKey;

    @Value("${jwt.expriration}")
    private long jwtExpiration;

    public String generateToken(String username) {

        //starts building JWT
        return Jwts.builder()

                //stores identity inside the token
                .setSubject(username)

                //when the token was created
                .setIssuedAt(new Date())

                //token expiry time
        .setExpiration(
                new Date(System.currentTimeMillis() + 1000 * 60 * 60)
        )
                //signs token using algorithm and seret key
                .signWith(
                        SignatureAlgorithm.HS256,
                        secretKey
                )
                .compact();//converts JWT into a final string


    }

}
