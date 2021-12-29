package hello.login.web.exceptions.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ServletExceptionController {

    @RequestMapping("/error-ex")
    public void errorEx() {
        throw new RuntimeException("runtime exception^^");
    }

    @RequestMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @RequestMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletResponse response, HttpServletRequest request) {
        log.info("errorPage404");
        return "/error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletResponse response, HttpServletRequest request) {
        log.info("errorPage500");
        return "/error-page/500";
    }

    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> error500Api(HttpServletResponse response, HttpServletRequest request) {
        log.info("api error page 500");
        Map<String, Object> result = new HashMap<>();
        Exception ex = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        result.put("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        result.put("message", ex.getMessage());

        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));
    }
}
