package WatchQuest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import WatchQuest.demo.service.ServiceFilm;
import jakarta.servlet.http.HttpSession;

@Controller
public class AppController {

    @Autowired
    private ServiceFilm serviceFilm;

    @GetMapping("/test")
    public String test() {
        return "main";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("film", serviceFilm.findById(3L));
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
        if (session.getAttribute("ruolo") != null) {
            return "redirect:/media/utente";
        } else {
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

    @GetMapping("/media")
    public String media(@RequestParam String titolo,@RequestParam String durata,@RequestParam String genere,@RequestParam String anno, Model model) {
        model.addAttribute("titolo", titolo);
        model.addAttribute("durata", durata);
        model.addAttribute("genere", genere);
        model.addAttribute("anno", anno);
        return "media";
    }

    // @GetMapping("/error")
    // public String errore(Model model) {
    // model.addAttribute("error","Qualcosa non ha funzionato");
    // return "errore";
    // }

}
