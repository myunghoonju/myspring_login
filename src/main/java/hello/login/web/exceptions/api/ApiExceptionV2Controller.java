package hello.login.web.exceptions.api;

import hello.login.domain.member.MemberDto;
import hello.login.web.exceptions.exception.UserException;
import hello.login.web.exceptions.exceptionHandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionV2Controller {


    private static final String EXAMPLE = "ex";
    private static final String BAD_REQUEST = "bad";
    private static final String USER_EX = "user-ex";

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {

        if (EXAMPLE.equals(id)) {
            throw new RuntimeException("wrong user");
        }

        if (BAD_REQUEST.equals(id)) {
            throw new IllegalArgumentException("wrong request");
        }

        if (USER_EX.equals(id)) {
            throw new UserException("user error");
        }

        return new MemberDto("spring", "hello");
    }

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
