package WatchQuest.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/test")
    public String test() {
        return "main";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus";
    }

    @GetMapping("/joinus")
    public String joinus() {
        return "joinus";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

}
