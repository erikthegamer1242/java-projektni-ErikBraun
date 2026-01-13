package braun.erik.prijevoz.model.exceptions;

/**
 * Exception thrown when entered year is negative
 * @author erik
 * @version 1.0
 */
public class YearNegativeException extends RuntimeException {

    /**
     * Constructs a new exception with the specified cause.
     *
     * @param cause the cause
     */
    public YearNegativeException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public YearNegativeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified message.
     * @param message the detail message
     */
    public YearNegativeException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with a default message.
     */
    public YearNegativeException() {
        super("Year cannot be negative");
    }
}
