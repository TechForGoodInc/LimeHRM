package limehrm.mappings;

import io.javalin.Javalin;

import limehrm.authentication.AuthenticationResponse;
import limehrm.exceptions.InvalidAuthCredentialsException;
import limehrm.exceptions.InvalidGrantTypeException;
import limehrm.hibernate.dao.UserDao;
import limehrm.hibernate.model.User;
import limehrm.util.AuthUtil;
import limehrm.util.JwtUtil;
import limehrm.util.LoggerUtil;
import java.security.KeyPair;

public class UrlMappings {
    private static final LoggerUtil logger = new LoggerUtil(UrlMappings.class.getSimpleName());
    
    private final Javalin app;
    private final KeyPair keyPair;
    
    public UrlMappings(Javalin app, KeyPair keyPair) {
        this.app = app;
        this.keyPair = keyPair;
        
        AuthenticationMappings();
    }
    
    private void AuthenticationMappings() {

        
        // TODO: Move this to AuthenticationController and AuthenticationService
        app.post("/api/authentication/getToken", ctx -> {
            logger.logTrace("Validating parameters");

            // grantTypes: "password", "google_id", "microsoft_id"
            String grantType = ctx.queryParam("grant_type", String.class).get();
            String userId = ctx.queryParam("userId", String.class).get();
           
            User databaseFetchedUser = UserDao.getUser(userId); // shouldn't be null
            logger.logWarn(userId);
            // Todo: implement microsoft, google auth
            if (grantType.equals("password")) {
                String password = ctx.queryParam("password", String.class).get();
                logger.logWarn(password);
                if (AuthUtil.verifyPassword(password.toCharArray(), databaseFetchedUser.getPassword())) {
                    ctx.status(200).json(new AuthenticationResponse(JwtUtil.generateToken(databaseFetchedUser, this.keyPair.getPrivate())));
                } else {
                   throw new InvalidAuthCredentialsException();
                }
            } else {
                throw new InvalidGrantTypeException();
            }
        });
    }
}
