package WatchQuest.demo.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import WatchQuest.demo.service.ServiceFilm;

@Controller
@RequestMapping("/film")
public class ControllerFilm {

    @Autowired
    private ServiceFilm serviceFilm;

    @GetMapping("/all")
    public String allFilm(Model model) {
        model.addAttribute("lista", serviceFilm.findAll());
        return "film";
    }

    @PostMapping("/modifica")
    public String modificaFilm(@RequestParam Map<String, String> parametri) {
        serviceFilm.update(parametri);
        return "redirect:/home";
    }

    @PostMapping("/inserisci")
    public String inserisciFilm(@RequestParam Map<String, String> parametri) {
        serviceFilm.save(parametri);
        return "redirect:/home";
    }

    @GetMapping("/elimina")
    public String eliminaFilm(@RequestParam(defaultValue = "0L") Long idFilm) {
        if (idFilm != 0) {
            serviceFilm.delete(idFilm);
        }
        return "redirect:/home";
    }

    // sopra ok
    // sotto tentativi per visualizzare film in base all'utente

    @GetMapping("/byUtente")
    public String allUtente(@RequestParam Long id, Model model) {
        model.addAttribute("lista", serviceFilm.findFilmByUtente(id));
        return "filmUtente";
    }
}
