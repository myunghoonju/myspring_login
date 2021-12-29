package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static hello.login.web.SessionConst.LOGIN_MEMBER;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("LoginCheckInterceptor start preHandle {}", requestURI);

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(LOGIN_MEMBER.getSessionName()) == null) {
            log.info("fail to authorise");
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }
}
