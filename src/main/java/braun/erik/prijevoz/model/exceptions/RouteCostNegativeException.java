package braun.erik.prijevoz.model.exceptions;

/**
 * Exception thrown when route cost entered is negative
 * @author erik
 * @version 1.0
 */
public class RouteCostNegativeException extends RuntimeException {

    /**
     * Constructs a new exception with a default message.
     */
    public RouteCostNegativeException() {
        super("Route cost cannot be negative");
    }

    /**
     * Constructs a new exception with the specified message.
     *
     * @param message the detail message
     */
    public RouteCostNegativeException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public RouteCostNegativeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public RouteCostNegativeException(Throwable cause) {
        super(cause);
    }
}
