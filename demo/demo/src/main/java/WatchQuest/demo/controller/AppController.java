package WatchQuest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import WatchQuest.demo.entity.Utente;
import WatchQuest.demo.service.ServiceFilm;
import WatchQuest.demo.service.ServiceSerie;
import jakarta.servlet.http.HttpSession;

@Controller
public class AppController {

    @Autowired
    private ServiceFilm serviceFilm;

    @Autowired
    private ServiceSerie serviceSerie;

    @GetMapping("/ricerca")
    public String test(Model model, HttpSession session) {
        fotoETasti(model, session);
        return "ricerca.html";
    }

    private void fotoETasti(Model model, HttpSession session) {
        if (session.getAttribute("loggato") != null) {
            // se è loggato
            model.addAttribute("loggato", session.getId());
            Object o = session.getAttribute("utente");
            if (o instanceof Utente utente) {
                model.addAttribute("foto", utente.getFoto_profilo());
            }
        }
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        fotoETasti(model, session);
        model.addAttribute("listaSerie", serviceSerie.find8());
        model.addAttribute("listaFilm", serviceFilm.find8());
        return "home";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }

    @GetMapping("/aboutus")
    public String aboutus(Model model, HttpSession session) {
        fotoETasti(model, session);
        return "aboutus";
    }

    @GetMapping("/joinus")
    public String joinus(Model model, HttpSession session) {
        fotoETasti(model, session);
        return "joinus";
    }

    @GetMapping("/faq")
    public String faq(Model model, HttpSession session) {
        fotoETasti(model, session);
        return "faq";
    }

    @GetMapping("/news")
    public String news(Model model, HttpSession session) {
        fotoETasti(model, session);
        return "news";
    }

    @GetMapping("/quiz")
    public String quiz(Model model, HttpSession session) {
        fotoETasti(model, session);
        return "quiz";
    }

    @GetMapping("/registrazione")
    public String registrazione(Model model, HttpSession session) {
        fotoETasti(model, session);
        return "registrazione";
    }

    @GetMapping("/media")
    public String media(@RequestParam String titolo, @RequestParam String wiki, @RequestParam String trailer,
            @RequestParam String descrizione, @RequestParam String durata, @RequestParam String image,
            @RequestParam String genere, @RequestParam String anno, Model model, HttpSession session, @RequestParam String id) {
        fotoETasti(model, session);
        session.setAttribute("id", id);
        model.addAttribute("titolo", titolo);
        model.addAttribute("durata", durata);
        model.addAttribute("genere", genere);
        model.addAttribute("anno", anno);
        model.addAttribute("descrizione", descrizione);
        model.addAttribute("wiki", wiki);
        model.addAttribute("trailer", trailer);
        model.addAttribute("image", image);
        return "media";
    }

    @GetMapping("/myLists")
    public String myLists(Model model, HttpSession session) {
        if (session.getAttribute("loggato") != null) {
            return "redirect:/media/utente";
        }
        return "redirect:/home";
    }
    

    @GetMapping("/settings")
    public String settings(Model model, HttpSession session) {
        if (session.getAttribute("loggato") != null) {
            Object o = session.getAttribute("utente");
            if (o instanceof Utente utente) {
                model.addAttribute("utente", utente);
                model.addAttribute("foto", utente.getFoto_profilo());
                model.addAttribute("loggato", session.getId());
                return "settings";
            }
        }
        return "redirect:/home";
    }

}
