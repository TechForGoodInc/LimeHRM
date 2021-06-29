package limehrm.authentication;

public class AuthenticationPublicKeyResponse {
    private String publicKey;
    
    public AuthenticationPublicKeyResponse() {}
    
    public AuthenticationPublicKeyResponse(String publicKey) {
        setPublicKey(publicKey);
    }
    
    public String getPublicKey() {
        return publicKey;
    }
    
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
