package hello.typeConverter.converter;

import hello.login.web.typeConverter.converter.IntegerToStringConverter;
import hello.login.web.typeConverter.converter.IpPortToStringConverter;
import hello.login.web.typeConverter.converter.StringToIntegerConverter;
import hello.login.web.typeConverter.converter.StringToIpPortConverter;
import hello.login.web.typeConverter.type.IpPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {

    @Test
    @DisplayName("convert string to integer")
    void convert1() {
        StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
        Integer result = stringToIntegerConverter.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("convert integer to string")
    void convert2() {
        IntegerToStringConverter integerToStringConverter = new IntegerToStringConverter();
        String result = integerToStringConverter.convert(10);
        assertThat(result).isEqualTo("10");
    }

    @Test
    @DisplayName("convert string to ip port")
    void convert3() {
        IpPortToStringConverter ipPortToStringConverter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = ipPortToStringConverter.convert(source);
        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    @DisplayName("convert  ip port to string")
    void convert4() {
        StringToIpPortConverter stringToIntegerConverter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = stringToIntegerConverter.convert(source);
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

}
