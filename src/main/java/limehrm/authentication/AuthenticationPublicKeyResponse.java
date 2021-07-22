package limehrm.authentication;

public class AuthenticationPublicKeyResponse {
    private String publicKey;
    
    public AuthenticationPublicKeyResponse() {}
    
    public AuthenticationPublicKeyResponse(String publicKey) {
        setPublicKey(publicKey);
    }
    
    
    /** 
     * @return String
     */
    public String getPublicKey() {
        return publicKey;
    }
    
    
    /** 
     * @param publicKey
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
