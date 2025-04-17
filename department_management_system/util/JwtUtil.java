package com.example.department_management_system.util;

import com.example.department_management_system.dto.auth.JwtDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final int tokenLiveTime = 1000 * 3600 * 24; // 1 day
    //private static final int tokenLiveTime = 1000 ; // 1 day

    private static final String secretKey = "veryLongSecretmazgillattayevlasharaaxmojonjinnijonsurbetbekkiydirhonuxlatdibekloxovdangasabekochkozjonduxovmashaynikmaydagapchishularnioqiganbolsangizgapyoqaniqsizmazgi";
    public static String encode(Integer id, String name, String surname, String role, String position, String status, Integer departmentId, Boolean visible, String email) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", id);
        extraClaims.put("name", name);
        extraClaims.put("surname", surname);
        extraClaims.put("role", role);
        extraClaims.put("position", position);
        extraClaims.put("status", status);
        extraClaims.put("departmentId", departmentId);
        extraClaims.put("visible", visible);
        extraClaims.put("email", email);

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenLiveTime))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static JwtDTO decode(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        Integer id = (Integer) claims.get("id");
        String name = (String) claims.get("name");
        String surname = (String) claims.get("surname");
        String role = (String) claims.get("role");
        String position = (String) claims.get("position");
        String status = (String) claims.get("status");
        Integer departmentId = (Integer) claims.get("departmentId");
        Boolean visible = (Boolean) claims.get("visible");
        String email = (String) claims.get("email");

        return new JwtDTO(id, name, surname, role, position, status, departmentId, visible, email);
    }

    private static SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
