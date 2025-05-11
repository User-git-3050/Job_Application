package az.msjobauth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    public static final String SECRET = "hb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0K";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token , Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(Authentication authentication){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Map<String,String> claims = new HashMap<>();
        claims.put("role",userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","))
        );
        return createToken(claims,userDetails);
    }

    public String createToken(Map<String,String> claims,UserDetails userDetails){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS512).compact();
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
