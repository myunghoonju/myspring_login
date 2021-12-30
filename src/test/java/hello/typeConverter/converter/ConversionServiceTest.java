package hello.typeConverter.converter;

import hello.login.web.typeConverter.converter.IntegerToStringConverter;
import hello.login.web.typeConverter.converter.IpPortToStringConverter;
import hello.login.web.typeConverter.converter.StringToIntegerConverter;
import hello.login.web.typeConverter.converter.StringToIpPortConverter;
import hello.login.web.typeConverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {

    @Test
    void conversion1() {
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        defaultConversionService.addConverter(new StringToIntegerConverter());
        defaultConversionService.addConverter(new IntegerToStringConverter());
        defaultConversionService.addConverter(new StringToIpPortConverter());
        defaultConversionService.addConverter(new IpPortToStringConverter());

        assertThat(defaultConversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(defaultConversionService.convert(10, String.class)).isEqualTo("10");

        IpPort ipPort = defaultConversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        String ipPortString = defaultConversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(ipPortString).isEqualTo("127.0.0.1:8080");
    }
}
