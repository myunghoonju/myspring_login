package hello.login.web.typeConverter.controller;

import hello.login.web.typeConverter.type.FormatterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class FormatterController {

    @GetMapping("/api/formatter/edit")
    public String formatterForm(Model model) {
        FormatterForm form = new FormatterForm();
        form.setNumber(10000);
        form.setLocalDateTime(LocalDateTime.now());
        model.addAttribute("form", form);

        return "converters/formatter-form";
    }

    @PostMapping("/api/formatter/edit")
    public String formatterEdit(@ModelAttribute("form") FormatterForm form) {
        return "converters/formatter-view";
    }
}
