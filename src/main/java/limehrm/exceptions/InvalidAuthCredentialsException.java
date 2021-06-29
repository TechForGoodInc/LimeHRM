package limehrm.exceptions;

public class InvalidAuthCredentialsException extends RuntimeException {
    public InvalidAuthCredentialsException() {
        super("Invalid Auth Credentials (Client ID, Client Secret, Username, Password)");
    }
}
