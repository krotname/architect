package name.krot.spacebatleserver.exceptions;

public class RotateException extends RuntimeException{

    public RotateException(Throwable cause) {
        super(cause);
    }

    public RotateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RotateException(String message) {
        super(message);
    }

    public RotateException() {
    }
}
