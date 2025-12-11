package braun.erik.prijevoz.model.exceptions;

/**
 * Exception thrown when route cost entered is negative
 * @author erik
 * @version 1.0
 */
public class RouteCostNegativeException extends RuntimeException {

    public RouteCostNegativeException() {
        super("Route cost cannot be negative");
    }

    public RouteCostNegativeException(String message) {
        super(message);
    }

    public RouteCostNegativeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouteCostNegativeException(Throwable cause) {
        super(cause);
    }
}
