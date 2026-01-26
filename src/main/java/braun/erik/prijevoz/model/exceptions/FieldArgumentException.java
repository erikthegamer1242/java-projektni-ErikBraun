package braun.erik.prijevoz.model.exceptions;

/**
 * Exception thrown when field argument saving fails.
 *
 * @author erik
 * @version 1.0
 */
public class FieldArgumentException extends Exception {
    /**
     * Constructs a new exception with a default message.
     */
    public FieldArgumentException() {
        super("Error saving field argument");
    }

    /**
     * Constructs a new exception with the specified message.
     *
     * @param message the detail message
     */
    public FieldArgumentException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public FieldArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public FieldArgumentException(Throwable cause) {
        super(cause);
    }
}
