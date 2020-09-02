package limehrm;

import java.util.Map;

public class ErrorResponse {
    public int status;
    public String title;
    public String developerMessage;
    public String userMessage;
    public String moreInfo;
    
    public ErrorResponse(int status, String title, String developerMessage, String userMessage, String moreInfo) {
        this.status = status;
        this.title = title;
        this.developerMessage = developerMessage;
        this.userMessage = userMessage;
        this.moreInfo = moreInfo;
    }
}
