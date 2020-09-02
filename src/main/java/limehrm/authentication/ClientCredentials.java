package limehrm.authentication;

import java.util.Objects;

public class ClientCredentials {
    private String clientId;
    private String clientSecret;
    
    public ClientCredentials(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
    
    public String getClientId() {
        return clientId;
    }
    
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    public String getClientSecret() {
        return clientSecret;
    }
    
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientCredentials that = (ClientCredentials) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(clientSecret, that.clientSecret);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientSecret);
    }
}
