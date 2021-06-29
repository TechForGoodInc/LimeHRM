package limehrm.mappings;

import io.javalin.Javalin;
import io.javalin.core.security.BasicAuthCredentials;
import limehrm.authentication.AuthenticationController;
import limehrm.authentication.AuthenticationResponse;
//import limehrm.authentication.AuthenticationService;
import limehrm.exceptions.InvalidAuthCredentialsException;
import limehrm.exceptions.InvalidGrantTypeException;
import limehrm.hibernate.dao.UserDao;
import limehrm.hibernate.model.User;
import limehrm.util.AuthUtil;
import limehrm.util.JwtUtil;
import limehrm.util.LoggerUtil;
import org.json.JSONObject;

import java.security.KeyPair;

import static limehrm.hibernate.dao.UserDao.getUser;

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

//        // Get temporary keyPair for client hashing
//        app.get("/api/authentication/getToken", ctx -> {
//            String email = ctx.queryParam("email", String.class).get();
//            logger.logWarn(email);
//
//
////            ctx.status(200).json(AuthenticationService.generatePublicKeyForEmail(email));
//        });
        
        // TODO: Move this to AuthenticationController and AuthenticationService
        app.post("/api/authentication/getToken", ctx -> {
            logger.logTrace("Validating parameters");

            // grantTypes: "password", "google_id", "microsoft_id"
            String grantType = ctx.queryParam("grant_type", String.class).get();
            String email = ctx.queryParam("email", String.class).get();

            User databaseFetchedUser = UserDao.getUser(email); // shouldn't be null

            // Todo: implement microsoft, google auth
            if (grantType.equals("password")) {
                String password = ctx.queryParam("password", String.class).get();
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
