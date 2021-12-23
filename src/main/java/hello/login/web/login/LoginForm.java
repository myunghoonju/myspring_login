package hello.login.web.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter @NoArgsConstructor
public class LoginForm {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
