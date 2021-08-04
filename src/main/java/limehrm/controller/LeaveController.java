package limehrm.controller;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.*;
import limehrm.ErrorResponse;
import limehrm.exceptions.JsonDeserializationException;
import limehrm.hibernate.model.Leave;
import limehrm.responses.DeleteResponse;
import limehrm.responses.SavedResponse;
import limehrm.service.LeaveService;
import limehrm.util.LoggerUtil;

public class LeaveController {
    private static LoggerUtil logger = new LoggerUtil(LeaveController.class.getSimpleName());
    
   
    // TODO: https://github.com/tipsy/javalin-openapi-example
    // TODO: https://github.com/tipsy/javalin-openapi-example/blob/master/src/main/java/io/javalin/example/java/Leave/LeaveController.java
    @OpenApi(
            summary = "Create leave",
            operationId = "createLeave",
            path = "/api/leaves",
            method = HttpMethod.POST,
            tags = {"Leave"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Leave.class)}),
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = SavedResponse.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create(Context ctx) {
        logger.logDebug("POST: leave");

        Leave leave = getLeaveFromCtx(ctx);

        LeaveService.save(leave);

        ctx.header("Location", String.format("%s/api/leaves/%s", ctx.host(), leave.getLeaveId()));
        ctx.status(201).json(new SavedResponse());
    }
    
    @OpenApi(
            summary = "Get all leaves",
            operationId = "getAllLeaves",
            path = "/api/leaves",
            method = HttpMethod.GET,
            tags = {"Leave"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Leave[].class)}) // TODO: Fix this error
            }
    )
    public static void getAll(Context ctx) {
        logger.logDebug("GET: leave");
        
        ctx.status(200).json(LeaveService.getAllLeave());
    }
    
    @OpenApi(
            summary = "Get leave by ID",
            operationId = "getLeaveById",
            path = "/api/leaves/:leaveId",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "leaveId", type = Integer.class, description = "The leave ID")},
            tags = {"Leave"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Leave.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void getOne(Context ctx) {
        logger.logDebug("GET: leave/:leaveId");
        ctx.status(200).json(LeaveService.findById(getPathParamLeaveId(ctx)));
    }
    
    
   
    @OpenApi(
            summary = "Update Leave by ID",
            operationId = "updateLeaveById",
            path = "/api/leaves/:leaveId",
            method = HttpMethod.PATCH,
            pathParams = {@OpenApiParam(name = "leaveId", type = Integer.class, description = "The leave ID")},
            tags = {"Leave"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Leave.class)}),
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = SavedResponse.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void update(Context ctx) {
        logger.logDebug("PATCH: leaves/:leaveId");

        Leave leave = getLeaveFromCtx(ctx);
        leave.setLeaveId(getPathParamLeaveId(ctx));

        LeaveService.update(leave);
        ctx.status(200).json(new SavedResponse());
    }
    
    @OpenApi(
            summary = "Delete leave by ID",
            operationId = "deleteLeaveById",
            path = "/api/leaves/:leaveId",
            method = HttpMethod.DELETE,
            pathParams = {@OpenApiParam(name = "leaveId", type = Integer.class, description = "The leave ID")},
            tags = {"Leave"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = DeleteResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void delete(Context ctx) {
        logger.logDebug("DELETE: leaves/:leaveId");
        LeaveService.delete(getPathParamLeaveId(ctx));
        ctx.status(200).json(new DeleteResponse());
    }
    
    
    /** 
     * @param ctx
     * @return String
     */
    private static String getPathParamLeaveId(Context ctx) {
        return ctx.pathParam("leaveId", String.class).get();
    }
    
    
    /** 
     * @param ctx
     * @return Leave
     */
    private static Leave getLeaveFromCtx(Context ctx) {
        Leave leave;
        try {
            logger.logTrace("Converting JSON to Leave POJO object");
            leave = ctx.bodyAsClass(Leave.class);
        } catch (BadRequestResponse badRequest) {
            throw new JsonDeserializationException();
        }

        return leave;
    }
}