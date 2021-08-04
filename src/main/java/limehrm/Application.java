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
import limehrm.controller.LeaveController;
import limehrm.controller.RecruitmentController;
import limehrm.controller.UserController;
import limehrm.controller.WorkerController;
import limehrm.exceptions.InvalidCredentialsException;
import limehrm.hibernate.model.User;
import limehrm.mappings.ExceptionMappings;
import limehrm.mappings.UrlMappings;
import limehrm.util.JwtUtil;
import limehrm.util.LoggerUtil;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Application {
    
    
    /** 
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        LoggerUtil logger = LoggerUtil.getLogger("Application");
        
        // TODO:  PersonalPhone
        
        // Create and Store Keys
        KeyPair keyPair = JwtUtil.generateKeyPair();
    
        // Starting Javalin & Config (Authentication)
        logger.logInfo("Starting Javalin...");
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(getConfiguredOpenApiPlugin());
            config.defaultContentType = "application/json";
            config.enableCorsForAllOrigins();
            config.accessManager((handler, ctx, permittedRoles) -> {
                String authHeader = ctx.header("Authorization");
                // TODO: FIX EndsWith (not secure?)// 
                if (ctx.url().endsWith("/api/authentication/getToken") || !ctx.url().contains("/api/")) {
                    handler.handle(ctx);
                }
                else if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    logger.logWarn("Authorization Error! authHeader:" + authHeader);
                    throw new InvalidCredentialsException();
                }
                else {
                    User authenticatedUser = JwtUtil.parseToken(authHeader.substring(7), keyPair.getPublic());
                    handler.handle(ctx);
                }
            });
        }).routes(() -> {
            path("api", () -> {
                path("recruitments", () -> {
                    get(RecruitmentController::getAll);
                    post(RecruitmentController::create);
                    path(":recruitmentId", () -> {
                        get(RecruitmentController::getOne);
                        patch(RecruitmentController::update);
                        delete(RecruitmentController::delete);
                    });
                });
                path("leaves", () -> {
                    get(LeaveController::getAll);
                    post(LeaveController::create);
                    path(":leaveId", () -> {
                        get(LeaveController::getOne);
                        patch(LeaveController::update);
                        delete(LeaveController::delete);
                    });
                });
                path("users", () -> {
                    get(UserController::getAll);
                    post(UserController::create);
                    path(":userId", () -> {
                        get(UserController::getOne);
                        patch(UserController::update);
                        delete(UserController::delete);
                    });
                   
                });
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
        }).before((ctx) -> {
            logger.logInfo("{} {}", ctx.method(), ctx.path());
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
        new UrlMappings(app, keyPair);
    }
    
    
    /** 
     * @return OpenApiPlugin
     */
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
