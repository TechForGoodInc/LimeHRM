package limehrm.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import limehrm.hibernate.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.*;
import java.util.Date;

public class JwtUtil {
    private static final LoggerUtil logger = LoggerUtil.getLogger("JwtUtil");
    
    /**
     * Creates a KeyPair for use in encrypting JWT data. Returns KeyPair object containing Public and Private keys.
     *
     * @return KeyPair object containing public and private key.
     */
    public static KeyPair generateKeyPair() {
        KeyPairGenerator keyPairGenerator = null;
        
        try {
             keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    
            keyPairGenerator.initialize(1024);
    
        } catch (Exception ignored) {
            
        }
    
        assert keyPairGenerator != null;
        return keyPairGenerator.generateKeyPair();
    }
    
    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public static User parseToken(String token, PublicKey publicKey) {
        Claims body = Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
        
        User u = new User();
        u.setEmail(body.getSubject());
        u.setId(String.valueOf(body.get("userId")));
        u.setGoogleId(String.valueOf(body.get("googleId")));
        u.setMicrosoftId(String.valueOf(body.get("microsoftId")));
        
        return u;
    }
    
    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param user the user for which the token will be generated
     * @return the JWT token
     */
    public static String generateToken(User user, PrivateKey privateKey) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("id", user.getId());
        claims.put("googleId", user.getGoogleId());
        claims.put("microsoftId", user.getMicrosoftId());
        
        return Jwts.builder()
                .setSubject("Authentication")
                // Expiry in 15 minutes or 900000 ms
                .setExpiration(new Date(System.currentTimeMillis() + 900000))
                .setIssuer("LimeHRM")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }
}