package az.uni.unitech.error.exception;

public class UserAlreadyExistsException extends RuntimeException {

    private UserAlreadyExistsException(String message) {
        super(message);
    }

    public static UserAlreadyExistsException getInstance(String message) {
        return new UserAlreadyExistsException(message);
    }

}