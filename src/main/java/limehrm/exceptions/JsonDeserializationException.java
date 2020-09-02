package limehrm.exceptions;

public class JsonDeserializationException extends RuntimeException {
    public JsonDeserializationException() {
        super("Unable to deserialize JSON. Please check JSON for correct syntax and validate keys and values.");
    }
}
