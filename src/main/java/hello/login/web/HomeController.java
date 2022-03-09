package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentResolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(@Login Member loginMember, Model model) {
        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);

        return "loginHome";
    }

    @ResponseBody
    @GetMapping("/hometest")
    public String homeTest() {
        return "loginHome";
    }

/*
    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);

        return "loginHome";
    }


    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);

        return "loginHome";
    }

    private final SessionManager sessionManager;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        Member session = (Member)sessionManager.getSession(request);
        if (session == null) {
            return "home";
        }
        model.addAttribute("member", session);

        return "loginHome";
    }
 */
}