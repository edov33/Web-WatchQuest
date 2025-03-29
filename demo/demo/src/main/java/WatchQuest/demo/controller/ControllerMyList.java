package WatchQuest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import WatchQuest.demo.entity.Utente;
import WatchQuest.demo.service.ServiceFilm;
import WatchQuest.demo.service.ServiceSerie;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/media")
public class ControllerMyList {

    @Autowired
    private ServiceFilm serviceFilm;
    @Autowired
    private ServiceSerie serviceSerie;

    @GetMapping("/utente")
    public String media(Model model, HttpSession session) {
        Object o = session.getAttribute("utente");
        if (o instanceof Utente utente) {
            model.addAttribute("listaSerie", serviceSerie.findSerieByUtente(utente.getId()));
            model.addAttribute("listaFilm", serviceFilm.findFilmByUtente(utente.getId()));
            return "mylists";
        }
        return "home";
    }
}
