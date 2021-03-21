package com.goku.ecommerce.security;

import com.goku.ecommerce.domain.exception.InvalidRequestException;
import com.goku.ecommerce.domain.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;

import static java.util.Collections.singletonList;

@Slf4j
public class JWTUtil {

    public static final String SECRET = "A_SECRET";

    public static String createJwt(String username) throws Exception {
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);

        final HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", username);
        claims.put("password", username);
        claims.put("authorities", singletonList(new SimpleGrantedAuthority("USER_ROLE")));
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setSubject("subject")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30000 * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes(Charset.forName("UTF-8")));

        return builder.compact();
    }

    public static Claims validateToken(String token) {
        token = token.replaceAll(" ", "").replace("Bearer", "");
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token.replace("{", "").replace("}", "")).getBody();
        } catch (SignatureException e) {
            throw new UnauthorizedException();
        }
        return claims;
    }

    public static void validateUsername(String token, String username) throws InvalidRequestException {
        if (!validateToken(token).get("username").equals(username))
            throw new InvalidRequestException();
    }
}