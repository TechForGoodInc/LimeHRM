package limehrm.mappings;

import io.javalin.Javalin;
import io.javalin.http.InternalServerErrorResponse;
import limehrm.ErrorResponse;
import limehrm.exceptions.*;
import limehrm.util.LoggerUtil;

public class ExceptionMappings {
    private static final LoggerUtil logger = new LoggerUtil(ExceptionMappings.class.getSimpleName());
    
    private Javalin app;
    
    public ExceptionMappings(Javalin app) {
        this.app = app;
        
        DefaultExceptionMapping();
        InvalidCredentialsMapping();
        InvalidAuthCredentialsMapping();
        InvalidSqlMapping();
        DeserializationErrorMapping();
        KeyPairNotGeneratedMapping();
        PasswordNotEncryptedMapping();
        NotFoundMapping();
    }
    
    private void DefaultExceptionMapping() {
        // TODO: Add more mappings (see: https://javalin.io/documentation#default-responses)
        app.exception(InternalServerErrorResponse.class, (e, ctx) -> {
            logger.logWarn("Internal Server Error");
           ctx.status(500).json(new ErrorResponse(500, "Internal Server Error",
                   "Something went wrong with the application (it threw an error), please try to debug this issue and look at the logs for the stack trace",
                   "The application is not working, please try again or contact the developer",
                   "The application is not working. Please look at the stack trace for more information")); 
        });
    }
    
    private void InvalidCredentialsMapping() {
        app.exception(InvalidCredentialsException.class, (e, ctx) -> {
            logger.logWarn("Invalid Credentials Exception: Invalid Credentials");
            ctx.status(401).json(new ErrorResponse(401, "Invalid Credentials Exception", 
                    "Please pass in the correct credentials via the Authorization header (Bearer authentication). You can get the token by calling base_url/api/authentication/getToken",
                    "Please pass in the correct credentials to the application",
                    "The server was unable to authenticate your identity. Please provide the application with the correct token."));
        });
    }
    
    private void InvalidAuthCredentialsMapping() {
        app.exception(InvalidAuthCredentialsException.class, (e, ctx) -> {
            logger.logWarn("Invalid Auth Credentials Exception: Invalid Auth Credentials");
            ctx.status(401).json(new ErrorResponse(401, "Invalid Auth Credentials Exception",
                    "Please pass in the correct clientId and clientSecret via the Authorization header (Basic base64{clientId:clientSecret})",
                    "Please pass in the correct credentials to the application",
                    "The server was unable to authenticate your identity. Please provide the application with the correct clientId, clientSecret, and username and password."));
        });
    }
    
    private void InvalidSqlMapping() {
        app.exception(DuplicateIDsSqlException.class, (e, ctx) -> {
            logger.logWarn("Runtime Sql Exception: Duplicate ID's");
            ctx.status(500).json(new ErrorResponse(500, "Duplicate ID's Exception",
                    "Please make sure that the ID doesn't already exist in the database. You can check if the row already exists by calling base_url/api/objects/{objectId}. For example, base_url/api/workers/{workerId}.",
                    "Please check that you are not re-creating anything in the database or creating something with an ID that already exists (check the ID)",
                    "The database cannot contain duplicate ID's. Please check that you are passing in the correct ID and that you are not re-creating an existing row in the database."));
        });
    }
    
    private void DeserializationErrorMapping() {
        app.exception(JsonDeserializationException.class, (e, ctx) -> {
            logger.logWarn("Json Deserialization Exception: Unable to Deserialize JSON");
            ctx.status(400).json(new ErrorResponse(400, "Json Deserialization Exception",
                    "Please check the JSON body and confirm that all the keys and values are valid",
                    "Please check that you are passing in the correct values to the application",
                    "Unable to deserialize JSON. Please check JSON body and pass in valid keys and values"));
        });
    }
    
    private void KeyPairNotGeneratedMapping() {
        app.exception(KeyPairNotGeneratedException.class, (e, ctx) -> {
            logger.logWarn("Key Pair Not Generated Yet for Email");
            ctx.status(500).json(new ErrorResponse(500, "Key Pair Not Yet Generated for Email",
                    "Please make sure to pass a get request to /api/getToken with an email in the query",
                    "Please contact your technology department for assistance",
                    "The server was unable to find an Key Pair for the given email. Please generate a Key Pair with /api/getToken GET request"));
        });
    }
    
    private void PasswordNotEncryptedMapping() {
        app.exception(PasswordNotEncryptedException.class, (e, ctx) -> {
            logger.logWarn("Password not Encrypted with Public Key");
            ctx.status(500).json(new ErrorResponse(500, "Password Not Encrypted",
                    "Please make sure to encrypt the password with the generated Public Key",
                    "Please contact your technology department for assistance",
                    "The server was unable to decrypt the password provided from the public key. Please make sure to encrypt the password and/or use the correct public key for the email provided"));
        });
    }
    
    private void NotFoundMapping() {
        app.exception(ResourceNotFoundException.class, (e, ctx) -> {
            logger.logWarn("Resource Not Found Exception: Unable to find the resource");
            ctx.status(404).json(new ErrorResponse(404, "Resource Not Found Exception",
                    "Please make sure that you are passing in the correct identifier and that the resource does exist",
                    "Please make sure that the resource exists",
                    "Program is not able to find the resource, please check the identifier and check if the resource exists"));
        });
    }
}
