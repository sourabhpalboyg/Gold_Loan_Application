package exception;



/**
 * Custom exception used when a requested resource (like Customer or Loan Application)
 * is not found in the database.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}