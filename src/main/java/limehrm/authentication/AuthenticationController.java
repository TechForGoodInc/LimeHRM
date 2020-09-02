package limehrm.authentication;

import limehrm.exceptions.InvalidCredentialsException;
import limehrm.user.User;
import limehrm.util.JwtUtil;
import limehrm.util.LoggerUtil;

import java.security.PrivateKey;
import java.util.Map;

public class AuthenticationController {
    private static LoggerUtil logger = LoggerUtil.getLogger("Authentication");
    
    private static final Map userToClientCredentialsMap = Map.of(
            new User("Nathan", 1), new ClientCredentials("insomnia", "V&xg.fYZ=8r+acEh")
    ); // TODO: Implement better solution?
    
    public static String getAuthenticationToken(PrivateKey privateKey, User user, ClientCredentials clientCredentials) throws InvalidCredentialsException {
        if (!userToClientCredentialsMap.containsKey(user) || !userToClientCredentialsMap.get(user).equals(clientCredentials)) {
            logger.logWarn("Invalid getAuthToken credentials with username: {}, id: {}, clientId: {}, clientSecret: {}", 
                    user.getUsername(), user.getId(), clientCredentials.getClientId(), clientCredentials.getClientSecret());
            
            throw new InvalidCredentialsException();
        } else {
            logger.logDebug("Token generated for User {}", user.getId());
            return JwtUtil.generateToken(user, privateKey);
        }
    }
}


