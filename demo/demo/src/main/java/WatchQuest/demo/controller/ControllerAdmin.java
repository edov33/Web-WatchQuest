package WatchQuest.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import WatchQuest.demo.entity.Utente;
import WatchQuest.demo.service.ServiceFilm;
import WatchQuest.demo.service.ServiceSerie;
import WatchQuest.demo.service.ServiceUtente;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ControllerAdmin {

    @Autowired
    private ServiceSerie serviceSerie;
    @Autowired
    private ServiceFilm serviceFilm;
    @Autowired
    private ServiceUtente serviceUtente;

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

    @GetMapping("/all")
    public String allUtente(Model model, HttpSession session) {
        fotoETasti(model, session);
        model.addAttribute("listaUtenti", serviceUtente.findAll());
        return "adminPages/infoUtenti.html";
    }

    @PostMapping("/modifica")
    public String modificaUtente(@RequestParam Map<String, String> parametri) {
        serviceUtente.update(parametri);
        return "redirect:/admin/all";
    }

    @PostMapping("/inserisci")
    public String inserisciUtente(@RequestParam Map<String, String> parametri) {
        serviceUtente.save(parametri);
        return "redirect:/admin/all";
    }

    @GetMapping("/eliminaUtente")
    public String eliminaUtente(@RequestParam(defaultValue = "0L") Long idUtente) {
        if (idUtente != 0) {
            serviceUtente.delete(idUtente);
        }
        return "redirect:/admin/all";
    }

    @GetMapping("/serie")
    public String serieUtenti(Model model, HttpSession session) {
        fotoETasti(model, session);
        model.addAttribute("listaSerie", serviceSerie.findAll());
        return "adminPages/allSerie.html";
    }

    @GetMapping("/film")
    public String filmUtenti(Model model, HttpSession session) {
        fotoETasti(model, session);
        model.addAttribute("listaFilm", serviceFilm.findAll());
        return "adminPages/allFilm.html";
    }

    @GetMapping("/all-film")
    public String allFilm() {
        return "adminPages/allFilm.html";
    }

}
