package limehrm.mappings;

import io.javalin.Javalin;
import limehrm.authentication.Authentication;
import limehrm.authentication.AuthenticationController;
import limehrm.authentication.ClientCredentials;
import limehrm.user.User;
import limehrm.util.LoggerUtil;

import java.security.KeyPair;

public class UrlMappings {
    private static final LoggerUtil logger = new LoggerUtil(UrlMappings.class.getSimpleName());
    
    private Javalin app;
    private KeyPair keyPair;
    
    
    public UrlMappings(Javalin app, KeyPair keyPair) {
        this.app = app;
        this.keyPair = keyPair;
        
        AuthenticationMappings();
    }
    
    private void AuthenticationMappings() {
        // TODO: Move this to AuthenticationController and AuthenticationService
        app.post("/api/authentication/getToken", ctx -> {
            logger.logDebug("POST: getToken");
            
            logger.logTrace("Validating parameters");
            String username = ctx.queryParam("username", String.class).get();
            int id = ctx.queryParam("id", Integer.class).get();
            
            String clientId = ctx.queryParam("client_id", String.class).get();
            String clientSecret = ctx.queryParam("client_secret", String.class).get();
            
            logger.logTrace("Getting authentication token");
            ctx.json(new Authentication(AuthenticationController.getAuthenticationToken(keyPair.getPrivate(), new User(username, id), new ClientCredentials(clientId, clientSecret))));
        });
    }
}