package hello.login.web.typeConverter.controller;

import hello.login.web.typeConverter.type.Form;
import hello.login.web.typeConverter.type.IpPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConvertController {

    @ResponseBody
    @GetMapping("/api/v2")
    public String convert(@RequestParam Integer data) {
        System.out.println("data = " + data);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/api/ipPort")
    public String convert2(@RequestParam IpPort ipPort) {
        System.out.println("ipPort ip = " + ipPort.getIp());
        System.out.println("ipPort port = " + ipPort.getPort());
        return "ok";
    }

    @GetMapping("/api/converter-view")
    public String converterView(Model model) {
        model.addAttribute("number", 10_000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8080));
        return "converters/converter-view";
    }

    @GetMapping("/api/converter-edit")
    public String converterForm(Model model) {
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        Form form = new Form(ipPort);
        model.addAttribute("form", form);

        return "converters/converter-form";
    }

    @PostMapping("/api/converter-edit")
    public String converterEdit(@ModelAttribute Form form, Model model) {
        IpPort ipPort = form.getIpPort();
        model.addAttribute("ipPort", ipPort);

        return "converters/converter-view";
    }


}
