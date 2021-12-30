package hello.login.web.typeConverter.controller;

import hello.login.web.typeConverter.type.IpPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConvertController {

    @GetMapping("/api/v2")
    public String convert(@RequestParam Integer data) {
        System.out.println("data = " + data);
        return "ok";
    }

    @GetMapping("/api/ipPort")
    public String convert2(@RequestParam IpPort ipPort) {
        System.out.println("ipPort ip = " + ipPort.getIp());
        System.out.println("ipPort port = " + ipPort.getPort());
        return "ok";
    }
}
