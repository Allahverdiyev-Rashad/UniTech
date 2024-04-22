package az.uni.unitech.error.exception;

public class DataNotFoundException extends RuntimeException {

    private DataNotFoundException(String message) {
        super(message);
    }

    public static DataNotFoundException getInstance(String message) {
        return new DataNotFoundException(message);
    }

}
