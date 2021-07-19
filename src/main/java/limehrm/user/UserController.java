package limehrm.user;
import io.javalin.http.BadRequestResponse;
import limehrm.responses.DeleteResponse;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.*;
import limehrm.ErrorResponse;
import limehrm.exceptions.JsonDeserializationException;
import limehrm.hibernate.model.User;
import limehrm.responses.SavedResponse;
import limehrm.util.LoggerUtil;


public class UserController {
    private static LoggerUtil logger = new LoggerUtil(UserController.class.getSimpleName());

    @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/api/users",
            method = HttpMethod.POST,
            tags = {"User"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = User.class)}),
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = SavedResponse.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create(Context ctx) {
        logger.logDebug("POST: user");

        User user = getUserFromCtx(ctx);

        UserService.save(user);

        ctx.header("Location", String.format("%s/api/users/%s", ctx.host(), user.getId()));
        ctx.status(201).json(new SavedResponse());
    }

    @OpenApi(
            summary = "Get all users",
            operationId = "getAllUsers",
            path = "/api/user",
            method = HttpMethod.GET,
            tags = {"User"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User[].class)}) // TODO: Fix this error
            }
    )
    public static void getAll(Context ctx) {
        logger.logDebug("GET: user");
        
        ctx.status(200).json(UserService.getAllUsers());
    }
    
    @OpenApi(
            summary = "Get user by ID",
            operationId = "getUserById",
            path = "/api/users/:userId",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"User"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void getOne(Context ctx) {
        logger.logDebug("GET: user/:userId");
        ctx.status(200).json(UserService.findById(getPathParamUserId(ctx)));
    }
    
    @OpenApi(
            summary = "Update user by ID",
            operationId = "updateUserById",
            path = "/api/users/:userId",
            method = HttpMethod.PATCH,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"User"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = User.class)}),
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = SavedResponse.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void update(Context ctx) {
        logger.logDebug("PATCH: users/:userId");

        User user = getUserFromCtx(ctx);
        user.setId(getPathParamUserId(ctx));

        UserService.update(user);
        ctx.status(200).json(new SavedResponse());
    }
    
    @OpenApi(
            summary = "Delete User by ID",
            operationId = "deleteUserById",
            path = "/api/users/:userId",
            method = HttpMethod.DELETE,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"User"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = DeleteResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void delete(Context ctx) {
        logger.logDebug("DELETE: users/:userId");
        UserService.delete(getPathParamUserId(ctx));
        ctx.status(200).json(new DeleteResponse());
    }
    
    private static String getPathParamUserId(Context ctx) {
        return ctx.pathParam("userId", String.class).get();
    }

    private static User getUserFromCtx(Context ctx) {
        User user;
        try {
            logger.logTrace("Converting JSON to User POJO object");
            user = ctx.bodyAsClass(User.class);
        } catch (BadRequestResponse badRequest) {
            throw new JsonDeserializationException();
        }

        return user;
    }
}
