package az.uni.unitech.error.exception;

public class AccountAlreadyExistsException extends RuntimeException {

    private AccountAlreadyExistsException(String message) {
        super(message);
    }

    public static AccountAlreadyExistsException getInstance(String message) {
        return new AccountAlreadyExistsException(message);
    }

}
