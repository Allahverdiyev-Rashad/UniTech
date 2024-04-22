package az.uni.unitech.error;

public final class ErrorMessage {

    private ErrorMessage() {
    }

    public static final String USER_ALREADY_EXISTS = "User already exists : ";
    public static final String NOT_FOUND = "%s not found : ";
    public static final String USER_CREDENTIALS_NOT_VALID = "User credentials not valid : ";
    public static final String ACCOUNT_ALREADY_EXISTS = "Account already exists : ";
    public static final String DISABLE_ACCOUNT = "Account is disable : ";
    public static final String INSUFFICIENT_FUNDS = "There are insufficient funds in the account : ";
    public static final String SAME_ACCOUNT = "You cannot transfer money to the same account : ";
    public static final String INVALID_DATA = "Invalid date or data not found : ";
    public static final String INVALID_PAIR = "Invalid currency pair or date : ";

}