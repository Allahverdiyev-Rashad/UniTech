package az.uni.unitech.error;

import az.uni.unitech.error.exception.DataNotFoundException;
import az.uni.unitech.error.exception.AccountAlreadyExistsException;
import az.uni.unitech.error.exception.IllegalArgumentException;
import az.uni.unitech.error.exception.IncorrectPasswordException;
import az.uni.unitech.error.exception.UserAlreadyExistsException;
import java.nio.file.AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        log.error("handleUserAlreadyExistsException {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(DataNotFoundException exception) {
        log.error("handleUserNotFoundException {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectPasswordException(IncorrectPasswordException exception) {
        log.error("handleIncorrectPasswordException {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAccountAlreadyExistsException(AccountAlreadyExistsException exception) {
        log.error("handleAccountAlreadyExistsException {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error("handleIllegalArgumentException {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException exception) {
        log.error("handleAccessDeniedException {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.FORBIDDEN.name(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        String fieldName = exception.getBindingResult().getFieldError().getField();
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        log.error("Validation error : {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.name(), fieldName + message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnknownException(Exception exception) {
        log.error("handleUnknownException {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.getMessage());
    }

}