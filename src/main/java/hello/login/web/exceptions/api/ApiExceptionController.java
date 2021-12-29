package hello.login.web.exceptions.api;

import hello.login.domain.member.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionController {

    private static final String EXAMPLE = "ex";

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (EXAMPLE.equals(id)) {
            throw new RuntimeException("wrong user");
        }

        return new MemberDto("spring", "hello");
    }


}
