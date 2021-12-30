package hello.login.web.exceptions.exceptionHandler.advice;

import hello.login.web.exceptions.exception.UserException;
import hello.login.web.exceptions.exceptionHandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final String BAD_REQUEST = "bad";
    private static final String USER_EX = "user-ex";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExceptionHandler(IllegalArgumentException ex) {
        log.error("[exceptionHandler] ex", ex);
        return new ErrorResult(BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExceptionHandler(UserException userException) {
        log.error("[userExceptionHandler] uex", userException);
        ErrorResult errorResult = new ErrorResult(USER_EX, userException.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exceptionHandler(Exception ex) {
        log.error("[exceptionHandler] ex", ex);
        return new ErrorResult("INTERNAL_SERVER_ERROR", ex.getMessage());
    }

}
