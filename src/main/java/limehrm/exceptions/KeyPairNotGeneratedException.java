package limehrm.exceptions;

public class KeyPairNotGeneratedException extends RuntimeException {
    public KeyPairNotGeneratedException() {
        super("Key pair not generated yet for email");
    }
}
