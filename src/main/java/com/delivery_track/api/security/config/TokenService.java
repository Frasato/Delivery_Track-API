package com.delivery_track.api.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.delivery_track.api.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Value("${api.security.jwt.secret}")
    private String secret;

    public String generateToken(User user){
        logger.info("Init to generate token to user {}", user.getId());
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("delivery-track")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
            logger.info("Token generated success to user {}", user.getId());
            return token;
        }catch(JWTCreationException jwtException){
            logger.error("Error on generate token to user {} - {}", user.getId(), jwtException.getMessage());
            throw new RuntimeException("ERROR: can't create token for user! "
                    + jwtException + " Message: " + jwtException.getMessage());
        }
    }

    public String validateToken(String token){
        logger.info("Starting validate token");
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("delivery-track")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException jwtVerification){
            logger.error("Error on validate token: {}", jwtVerification.getMessage());
            throw new RuntimeException("ERROR: token not valid " + jwtVerification.getMessage());
        }
    }

    public Instant expirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
