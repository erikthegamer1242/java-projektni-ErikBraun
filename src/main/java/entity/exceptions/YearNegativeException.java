package entity.exceptions;

/**
 * Exception thrown when entered year is negative
 * @author erik
 * @version 1.0
 */
public class YearNegativeException extends RuntimeException { // NOSONAR: Custom exception, not an empty class

    public YearNegativeException(Throwable cause) {
        super(cause);
    }

    public YearNegativeException(String message, Throwable cause) {
        super(message, cause);
    }

    public YearNegativeException(String message) {
        super(message);
    }

    public YearNegativeException() {
        super("Year cannot be negative");
    }
}
