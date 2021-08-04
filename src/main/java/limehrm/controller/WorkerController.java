package limehrm.controller;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.*;
import limehrm.ErrorResponse;
import limehrm.exceptions.JsonDeserializationException;
import limehrm.hibernate.model.Worker;
import limehrm.responses.DeleteResponse;
import limehrm.responses.SavedResponse;
import limehrm.service.WorkerService;
import limehrm.util.LoggerUtil;

public class WorkerController {
    private static LoggerUtil logger = new LoggerUtil(WorkerController.class.getSimpleName());
    
    
    /** 
     * @param worker"
     * @param "createWorker"
     * @param "/api/workers"
     * @param HttpMethod.POST
     * @param @OpenApiRequestBody(
     */
    // TODO: https://github.com/tipsy/javalin-openapi-example
    // TODO: https://github.com/tipsy/javalin-openapi-example/blob/master/src/main/java/io/javalin/example/java/worker/WorkerController.java
    @OpenApi(
            summary = "Create worker",
            operationId = "createWorker",
            path = "/api/workers",
            method = HttpMethod.POST,
            tags = {"Worker"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Worker.class)}),
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = SavedResponse.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create(Context ctx) {
        logger.logDebug("POST: worker");

        Worker worker = getWorkerFromCtx(ctx);

        WorkerService.save(worker);

        ctx.header("Location", String.format("%s/api/workers/%s", ctx.host(), worker.getWorkerId()));
        ctx.status(201).json(new SavedResponse());
    }
    
    @OpenApi(
            summary = "Get all workers",
            operationId = "getAllWorkers",
            path = "/api/workers",
            method = HttpMethod.GET,
            tags = {"Worker"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Worker[].class)}) // TODO: Fix this error
            }
    )
    public static void getAll(Context ctx) {
        logger.logDebug("GET: worker");
        
        ctx.status(200).json(WorkerService.getAll());
    }
    
    @OpenApi(
            summary = "Get worker by ID",
            operationId = "getWorkerById",
            path = "/api/workers/:workerId",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "workerId", type = Integer.class, description = "The worker ID")},
            tags = {"Worker"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Worker.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void getOne(Context ctx) {
        logger.logDebug("GET: worker/:workerId");
        ctx.status(200).json(WorkerService.findById(getPathParamWorkerId(ctx)));
    }


//     @OpenApi(
//         summary = "Get leave by ID",
//         operationId = "getLeaveById",
//         path = "/api/workers/:leaveId",
//         method = HttpMethod.GET,
//         pathParams = {@OpenApiParam(name = "leaveId", type = Integer.class, description = "The leave ID")},
//         tags = {"Leave"},
//         responses = {
//                 @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Worker.class)}),
//                 @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
//         }
// )   
//     public static void getLeave(Context ctx) {
//         logger.logDebug("GET: worker/:leaveId");
//         ctx.status(200).json(WorkerService.getLeave(getPathParamLeaveId(ctx)));
//     }
    
    
    /** 
     * @param ID"
     * @param "updateWorkerById"
     * @param "/api/workers/:workerId"
     * @param HttpMethod.PATCH
     * @param @OpenApiRequestBody(
     */
    @OpenApi(
            summary = "Update worker by ID",
            operationId = "updateWorkerById",
            path = "/api/workers/:workerId",
            method = HttpMethod.PATCH,
            pathParams = {@OpenApiParam(name = "workerId", type = Integer.class, description = "The worker ID")},
            tags = {"Worker"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Worker.class)}),
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = SavedResponse.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void update(Context ctx) {
        logger.logDebug("PATCH: workers/:workerId");

        Worker worker = getWorkerFromCtx(ctx);
        worker.setWorkerId(getPathParamWorkerId(ctx));

        WorkerService.update(worker);
        ctx.status(200).json(new SavedResponse());
    }
    
    @OpenApi(
            summary = "Delete worker by ID",
            operationId = "deleteWorkerById",
            path = "/api/workers/:workerId",
            method = HttpMethod.DELETE,
            pathParams = {@OpenApiParam(name = "workerId", type = Integer.class, description = "The worker ID")},
            tags = {"Worker"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = DeleteResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void delete(Context ctx) {
        logger.logDebug("DELETE: workers/:workerId");
        WorkerService.delete(getPathParamWorkerId(ctx));
        ctx.status(200).json(new DeleteResponse());
    }
    
    
    /** 
     * @param ctx
     * @return String
     */
    private static String getPathParamWorkerId(Context ctx) {
        return ctx.pathParam("workerId", String.class).get();
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
     * @return Worker
     */
    private static Worker getWorkerFromCtx(Context ctx) {
        Worker worker;
        try {
            logger.logTrace("Converting JSON to Worker POJO object");
            worker = ctx.bodyAsClass(Worker.class);
        } catch (BadRequestResponse badRequest) {
            throw new JsonDeserializationException();
        }

        return worker;
    }
}