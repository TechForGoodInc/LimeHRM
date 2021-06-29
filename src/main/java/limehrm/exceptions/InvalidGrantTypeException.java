package limehrm.exceptions;

public class InvalidGrantTypeException extends RuntimeException {
    public InvalidGrantTypeException() {
        super("Invalid Grant Type");
    }
}
