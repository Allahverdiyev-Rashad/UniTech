package az.uni.unitech.error.exception;

public class IncorrectPasswordException extends RuntimeException {

    private IncorrectPasswordException(String message) {
        super(message);
    }

    public static IncorrectPasswordException getInstance(String message) {
        return new IncorrectPasswordException(message);
    }

}
