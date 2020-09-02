package limehrm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.ReDocOptions;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import limehrm.exceptions.InvalidCredentialsException;
import limehrm.mappings.ExceptionMappings;
import limehrm.mappings.UrlMappings;
import limehrm.util.JwtUtil;
import limehrm.util.LoggerUtil;
import limehrm.worker.WorkerController;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Application {
    
    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
        LoggerUtil logger = LoggerUtil.getLogger("Application");
        
        // TODO: Convert HomePhone to PersonalPhone
        
        // Create and Store Keys
        KeyPair keyPair = JwtUtil.generateKeyPair();
    
        // Starting Javalin & Config (Authentication)
        logger.logInfo("Starting Javalin...");
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(getConfiguredOpenApiPlugin());
            config.defaultContentType = "application/json";
            config.accessManager((handler, ctx, permittedRoles) -> {
                String authHeader = ctx.header("Authorization");
    
                // TODO: FIX
                if (ctx.url().endsWith("/api/authentication/getToken") || !ctx.url().contains("/api/")) {
                    handler.handle(ctx);
                } else if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                    ctx.status(401).json("No JWT token found in request headers"); // TODO: Make more generic
                    logger.logError("Authorization Error! authHeader:" + authHeader);
                    throw new InvalidCredentialsException();
                } else {
                    String authToken = authHeader.substring(7);
        
                    JwtUtil.parseToken(authToken, keyPair.getPublic());
                    handler.handle(ctx);
                }
            });
        }).routes(() -> {
            path("api", () -> {
                path("workers", () -> {
                    get(WorkerController::getAll);
                    post(WorkerController::create);
                    path(":workerId", () -> {
                        get(WorkerController::getOne);
                        patch(WorkerController::update);
                        delete(WorkerController::delete);
                    });
                }); 
            });
        }).start();
    
        // Configure Jackson object mapper
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule()); // Fix illegal reflective access warning
        
        JavalinJackson.configure(objectMapper);
        
        // TODO: Work on OpenAPI Docs for all REST APIs
        
        // Custom Converters
        new CustomConverters();
        
        // Exception Mappings
        new ExceptionMappings(app);
        
        // URL Mappings
        app.get("/", ctx -> ctx.result("Hello World"));
        new UrlMappings(app, keyPair, DatabaseConnector.dbUtil);
    }
    
    private static OpenApiPlugin getConfiguredOpenApiPlugin() {
        Info info = new Info().version("1.0").description("User API");
        OpenApiOptions options = new OpenApiOptions(info)
                .activateAnnotationScanningFor("limehrm")
                .path("/swagger-docs") // endpoint for OpenAPI json
                .swagger(new SwaggerOptions("/swagger-ui")) // endpoint for swagger-ui
                .reDoc(new ReDocOptions("/redoc")) // endpoint for redoc
                .defaultDocumentation(doc -> {
                    // TODO: Fix default 500 error response
                    doc.json("500", ErrorResponse.class);
                    doc.json("401", ErrorResponse.class);
                });
        return new OpenApiPlugin(options);
    }
}
