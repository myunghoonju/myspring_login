package hello.login.web.typeConverter.type;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode @AllArgsConstructor
public class IpPort {

    private String ip;
    private int port;
}
