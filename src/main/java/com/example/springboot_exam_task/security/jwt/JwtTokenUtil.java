package com.example.springboot_exam_task.security.jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtTokenUtil {
    @Value("AzizaDev")
    private String jwtSecret;
    private final static Long JWT_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000L;
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis())) //data vypuska tokena
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)) // srok godnosti tokena
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public String generatedToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    private <T> T getClaimFromToken(String token, Function<Claims, T> function) {
        final Claims claims = getClaimsFromToken(token);
        return function.apply(claims);

    }
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}


