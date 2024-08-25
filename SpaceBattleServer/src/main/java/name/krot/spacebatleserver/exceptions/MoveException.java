package name.krot.spacebatleserver.exceptions;

public class MoveException extends RuntimeException{
    public MoveException() {
    }

    public MoveException(String message) {
        super(message);
    }

    public MoveException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoveException(Throwable cause) {
        super(cause);
    }
}
