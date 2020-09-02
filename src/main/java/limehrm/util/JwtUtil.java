package limehrm.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import limehrm.user.User;

import java.security.*;
import java.util.Date;

public class JwtUtil {
    private static final LoggerUtil logger = LoggerUtil.getLogger("JwtUtil");
    
    /**
     * Creates a KeyPair for use in encrypting JWT data. Returns KeyPair object containing Public and Private keys.
     *
     * @return KeyPair object containing public and private key.
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        
        keyPairGenerator.initialize(1024);
        
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
        u.setUsername(body.getSubject());
        u.setId(Integer.parseInt(String.valueOf(body.get("userId"))));
        
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
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId", user.getId());
        
        return Jwts.builder()
                .setSubject("Authentication")
                // 86400000 milliSeconds / day (take current time & add one day)
                // Expiry in a day
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .setIssuer("LimeHRM")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }
}