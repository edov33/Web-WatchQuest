package WatchQuest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import WatchQuest.demo.entity.Utente;
import WatchQuest.demo.service.ServiceFilm;
import WatchQuest.demo.service.ServiceSerie;
import WatchQuest.demo.service.ServiceUtente;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/media")
public class ControllerMedia {

    @Autowired
    private ServiceFilm serviceFilm;
    @Autowired
    private ServiceSerie serviceSerie;
    @Autowired
    private ServiceUtente serviceUtente;

    @GetMapping("/utente")
    public String media(Model model, HttpSession session) {
        if (session.getAttribute("loggato") != null) {
            Object o = session.getAttribute("utente");
            if (o instanceof Utente utente) {
                model.addAttribute("listaSerie", serviceSerie.findSerieByUtente(utente.getId()));
                model.addAttribute("listaFilm", serviceFilm.findFilmByUtente(utente.getId()));
                model.addAttribute("loggato", session.getId());
                model.addAttribute("foto", utente.getFoto_profilo());
                return "mylists";
            }
        }
        return "home";
    }

    @GetMapping("/like")
    public String aggiungiLike(Model model, HttpSession session) {
        if (session.getAttribute("loggato") != null) {
            // System.out.println("-----------like-----------");
            Object o = session.getAttribute("utente");
            Object ob = session.getAttribute("id");
            if (o instanceof Utente utente) {
                serviceUtente.associaProgramma(utente.getId(), Long.parseLong(ob.toString()));
            }
            return "redirect:/media/utente";
        }
        return "redirect:/home";
    }
}
