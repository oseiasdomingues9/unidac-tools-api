package com.unidac.tools.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.unidac.tools.entities.User;
import com.unidac.tools.utils.DateUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtUtils {

    @Value("${authentication.jwt.secret}")
    private String secret;

    @Value("${authentication.cookies.token}")
    private String tokenCookie;

    @Value("${authentication.token_expiration.token_expiration}")
    private Integer hourToken;

    public ResponseCookie generateJwtCookie(User user) {
        String jwt = generateToken(user.getUsername());
        return ResponseCookie.from(tokenCookie, jwt).path("/api").maxAge(3600).httpOnly(true).build();
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, tokenCookie);
        if (cookie != null) return cookie.getValue();
        else  return null;
    }

    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie.from(tokenCookie,"").path("/api").build();
    }


    public String generateToken(String username){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(username)
                    .withExpiresAt(DateUtils.hourToInstant(hourToken))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro na geração do token");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
