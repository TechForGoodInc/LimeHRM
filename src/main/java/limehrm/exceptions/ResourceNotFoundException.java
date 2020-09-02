package limehrm.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Couldn't find the specified resource. Please double check identifier.");
    }
}
