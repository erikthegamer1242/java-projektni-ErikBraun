package braun.erik.prijevoz.model.exceptions;

/**
 * Exception thrown when database operations fail.
 *
 * @author erik
 * @version 1.0
 */
public class DatabaseException extends RuntimeException {
    /**
     * Constructs a new exception with a default message.
     */
    public DatabaseException() {
        super("There was an error working with the database");
    }

    /**
     * Constructs a new exception with the specified message.
     *
     * @param message the detail message
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public DatabaseException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified parameters.
     * @param message the detail message
     * @param cause the cause
     * @param enableSuppression whether suppression is enabled
     * @param writableStackTrace whether the stack trace is writable
     */
    public DatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
