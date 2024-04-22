package az.uni.unitech.error.exception;

public class IllegalArgumentException extends RuntimeException {

    private IllegalArgumentException(String message) {
        super(message);
    }

    public static IllegalArgumentException getInstance(String message) {
        return new IllegalArgumentException(message);
    }

}
