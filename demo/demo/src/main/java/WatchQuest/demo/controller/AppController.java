package WatchQuest.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

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

    @GetMapping("/myLists")
    public String myLists(HttpSession session) {
        if(session.getAttribute("ruolo") != null){
            return "redirect:/media/utente";
        }
        else{
            return "redirect:/home";
        }
    }
    
    @GetMapping("/news")
    public String news() {
        
        return "news";
    }

    @GetMapping("/quiz")
    public String quiz() {
        return "quiz";
    }
    
    @GetMapping("/settings")
    public String settings(Model model) {
        model.addAttribute("loggato", "qualcosa");
        return "settings";
    }

    @GetMapping("/registrazione")
    public String registrazione() {
        return "registrazione";
    }

 
    
    // @GetMapping("/error")
    // public String errore(Model model) {
    //     model.addAttribute("error","Qualcosa non ha funzionato");
    //     return "errore";
    // }



}
