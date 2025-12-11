package braun.erik.prijevoz.model.exceptions;

/**
 * Exception thrown when entered year is negative
 * @author erik
 * @version 1.0
 */
public class YearNegativeException extends RuntimeException {

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
