package com.practice.java.security.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;


@Service
public class JwtService {

    @Value("${key.jwt}")
    private String KEY_JWT;

    public String generateToken(UserDetails user, Map<String, Object> extraClaims){

        Integer exp_minutes = 5;
        Date issuedAt = new Date(System.currentTimeMillis());
        Date exp = new Date((60*1000* exp_minutes)+ issuedAt.getTime());

        String jwt = Jwts.builder()
                .setClaims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .setExpiration(exp)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }

    public boolean validate(String jwt){
        try {
        return Optional
                .ofNullable(claimSubject(jwt, Claims::getSubject))
                .isPresent();
        }catch (Exception ex){
            return false;
        }
    }

    public String claimSubject(String jwt, Function<Claims,String> getSubject){
        Claims claims = generateClaims(jwt);
        return getSubject.apply(claims);
    }

    private Claims generateClaims(String jwt) {
        return Jwts.parser().verifyWith(generateKey())
                .build().parseSignedClaims(jwt).getPayload();
    }

    private SecretKey generateKey() {
        byte[] bytesKey = Decoders.BASE64.decode(KEY_JWT);
        return Keys.hmacShaKeyFor(bytesKey);
    }
}
