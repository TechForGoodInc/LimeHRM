package limehrm.authentication;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Helper;
import limehrm.exceptions.InvalidCredentialsException;
import limehrm.hibernate.dao.UserDao;
import limehrm.hibernate.model.User;
import limehrm.mappings.UrlMappings;
import limehrm.util.JwtUtil;
import limehrm.util.LoggerUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;

public class AuthenticationController {
    private static final LoggerUtil logger = new LoggerUtil(AuthenticationController.class.getSimpleName());
    
//    public static String getAuthenticationToken(PrivateKey privateKey, String email, String password) throws InvalidCredentialsException {
//        logger.logWarn(password);
//
//        User user = UserDao.getUser(email);
//
//        if (user == null || user.getPassword().equals(password)) {
//            logger.logWarn("Invalid getAuthToken credentials with email: {}", email);
//
//            throw new InvalidCredentialsException();
//        } else {
//            logger.logDebug("Token generated for User {}", email);
//            return JwtUtil.generateToken(user, privateKey);
//        }
//    }
//
//    public static byte[] generateSalt() {
//        // CSPRNG: SecureRandom
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//
//        return salt;
//    }




}


