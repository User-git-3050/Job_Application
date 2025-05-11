package az.msjobgateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Component
public class JwtUtil {

    public static final String SECRET = "hb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0K" ;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractRole(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("role", String.class);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}