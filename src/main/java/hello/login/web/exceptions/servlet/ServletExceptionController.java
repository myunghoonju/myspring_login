package hello.login.web.exceptions.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
