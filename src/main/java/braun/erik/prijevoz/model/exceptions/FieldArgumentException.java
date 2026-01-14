package braun.erik.prijevoz.model.exceptions;

public class FieldArgumentException extends Exception {
    public FieldArgumentException() {
        super("Error saving field argument");
    }

    public FieldArgumentException(String message) {
        super(message);
    }

    public FieldArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldArgumentException(Throwable cause) {
        super(cause);
    }
}
