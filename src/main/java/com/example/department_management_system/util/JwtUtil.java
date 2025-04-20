package com.example.department_management_system.util;

import com.example.department_management_system.dto.auth.JwtDTO;
import com.example.department_management_system.entity.EmployeeEntity;
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

    public static String encode(EmployeeEntity entity ) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", entity.getId());
        extraClaims.put("name", entity.getName());
        extraClaims.put("surname", entity.getSurname());
        extraClaims.put("email", entity.getEmail());
        extraClaims.put("position", entity.getPosition());
        extraClaims.put("role", entity.getRole());
        extraClaims.put("status", entity.getStatus());
        extraClaims.put("departmentId", entity.getDepartmentId());
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(entity.getEmail())
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

    public static Integer decodeRegVerToken(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Integer.valueOf(claims.getSubject());
    }

    public static String encodeVer(Integer id) {

        return Jwts
                .builder()
                .subject(String.valueOf(id))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (60*60*1000)))
                .signWith(getSignInKey())
                .compact();
    }
}
