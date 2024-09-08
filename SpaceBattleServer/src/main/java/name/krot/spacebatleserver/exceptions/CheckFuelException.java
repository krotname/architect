package name.krot.spacebatleserver.exceptions;

public class CheckFuelException extends RuntimeException{
    public CheckFuelException() {
        super();
    }

    public CheckFuelException(String message) {
        super(message);
    }

    public CheckFuelException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckFuelException(Throwable cause) {
        super(cause);
    }
}
