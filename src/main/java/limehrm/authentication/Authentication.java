package limehrm.authentication;

public class Authentication {
    private String token;
    
    public Authentication(String token) {
        setToken(token);
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
}
