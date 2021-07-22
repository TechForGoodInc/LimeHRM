package limehrm.authentication;



public class AuthenticationResponse {
    private String token;
    
    public AuthenticationResponse() {}
    
    /**
     * 
     * @param 
     * @return the 
     */
    public AuthenticationResponse(String token) {
        setToken(token);
    }
     /**
     * 
     * @return the token
     */
    public String getToken() {
        return token;
    }
     /**
     * 
     * @param 
     */
    public void setToken(String token) {
        this.token = token;
    }
}
