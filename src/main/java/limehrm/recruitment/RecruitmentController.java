package limehrm.recruitment;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.*;
import limehrm.ErrorResponse;
import limehrm.exceptions.JsonDeserializationException;
import limehrm.hibernate.model.Recruitment;
import limehrm.responses.DeleteResponse;
import limehrm.responses.SavedResponse;
import limehrm.util.LoggerUtil;

public class RecruitmentController {
    private static LoggerUtil logger = new LoggerUtil(RecruitmentController.class.getSimpleName());
    
    
    /** 
     * @param recruitment"
     * @param "createRecruitment"
     * @param "/api/recruitments"
     * @param HttpMethod.POST
     * @param @OpenApiRequestBody(
     */
    // TODO: https://github.com/tipsy/javalin-openapi-example
    // TODO: https://github.com/tipsy/javalin-openapi-example/blob/master/src/main/java/io/javalin/example/java/Recruitment/RecruitmentController.java
    @OpenApi(
            summary = "Create recruitment",
            operationId = "createRecruitment",
            path = "/api/recruitments",
            method = HttpMethod.POST,
            tags = {"Recruitment"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Recruitment.class)}),
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = SavedResponse.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create(Context ctx) {
        logger.logDebug("POST: recruitment");

        Recruitment recruitment = getRecruitmentFromCtx(ctx);

        RecruitmentService.save(recruitment);

        ctx.header("Location", String.format("%s/api/recruitments/%s", ctx.host(), recruitment.getId()));
        ctx.status(201).json(new SavedResponse());
    }
    
    @OpenApi(
            summary = "Get all recruitments",
            operationId = "getAllRecruitments",
            path = "/api/recruitments",
            method = HttpMethod.GET,
            tags = {"Recruitment"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Recruitment[].class)}) // TODO: Fix this error
            }
    )
    public static void getAll(Context ctx) {
        logger.logDebug("GET: recruitment");
        
        ctx.status(200).json(RecruitmentService.getAllRecruitments());
    }
    
    @OpenApi(
            summary = "Get recruitment by ID",
            operationId = "getRecruitmentById",
            path = "/api/recruitments/:recruitmentId",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "recruitmentId", type = Integer.class, description = "The recruitment ID")},
            tags = {"Recruitment"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Recruitment.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void getOne(Context ctx) {
        logger.logDebug("GET: recruitment/:recruitmentId");
        ctx.status(200).json(RecruitmentService.findById(getPathParamRecruitmentId(ctx)));
    }
    
    
    /** 
     * @param ID"
     * @param "updateRecruitmentById"
     * @param "/api/recruitments/:recruitmentId"
     * @param HttpMethod.PATCH
     * @param @OpenApiRequestBody(
     */
    @OpenApi(
            summary = "Update Recruitment by ID",
            operationId = "updateRecruitmentById",
            path = "/api/recruitments/:recruitmentId",
            method = HttpMethod.PATCH,
            pathParams = {@OpenApiParam(name = "recruitmentId", type = Integer.class, description = "The recruitment ID")},
            tags = {"Recruitment"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Recruitment.class)}),
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = SavedResponse.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void update(Context ctx) {
        logger.logDebug("PATCH: recruitments/:recruitmentId");

        Recruitment recruitment = getRecruitmentFromCtx(ctx);
        recruitment.setId(getPathParamRecruitmentId(ctx));

        RecruitmentService.update(recruitment);
        ctx.status(200).json(new SavedResponse());
    }
    
    @OpenApi(
            summary = "Delete recruitment by ID",
            operationId = "deleteRecruitmentById",
            path = "/api/recruitments/:recruitmentId",
            method = HttpMethod.DELETE,
            pathParams = {@OpenApiParam(name = "recruitmentId", type = Integer.class, description = "The recruitment ID")},
            tags = {"Recruitment"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = DeleteResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void delete(Context ctx) {
        logger.logDebug("DELETE: recruitments/:recruitmentId");
        RecruitmentService.delete(getPathParamRecruitmentId(ctx));
        ctx.status(200).json(new DeleteResponse());
    }
    
    
    /** 
     * @param ctx
     * @return String
     */
    private static String getPathParamRecruitmentId(Context ctx) {
        return ctx.pathParam("recruitmentId", String.class).get();
    }
    
    
    /** 
     * @param ctx
     * @return Recruitment
     */
    private static Recruitment getRecruitmentFromCtx(Context ctx) {
        Recruitment recruitment;
        try {
            logger.logTrace("Converting JSON to Recruitment POJO object");
            recruitment = ctx.bodyAsClass(Recruitment.class);
        } catch (BadRequestResponse badRequest) {
            throw new JsonDeserializationException();
        }

        return recruitment;
    }
}