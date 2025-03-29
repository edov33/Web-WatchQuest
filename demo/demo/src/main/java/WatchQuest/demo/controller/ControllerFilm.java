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
        model.addAttribute("listaFilm", serviceFilm.findAll());
        return "adminPages/allFilm.html";
    }

    @PostMapping("/modifica")
    public String modificaFilm(@RequestParam Map<String, String> parametri) {
        serviceFilm.update(parametri);
        return "redirect:/film/all";
    }

    @PostMapping("/inserisci")
    public String inserisciFilm(@RequestParam Map<String, String> parametri) {
        serviceFilm.save(parametri);
        return "redirect:/film/all";
    }

    @GetMapping("/elimina")
    public String eliminaFilm(@RequestParam(defaultValue = "0L") Long idFilm) {
        if (idFilm != 0) {
            serviceFilm.delete(idFilm);
        }
        return "redirect:/film/all";
    }

    @GetMapping("/byTitolo")
    public String filmByTitolo(@RequestParam String titolo, Model model) {
        model.addAttribute("listaFilm", serviceFilm.findFilmByTitolo(titolo));
        return "adminPages/allFilm.html";
    }

    @GetMapping("/byGenere")
    public String filmByGenere(@RequestParam String genere, Model model) {
        model.addAttribute("listaFilm", serviceFilm.findFilmByGenere(genere));
        return "adminPages/allFilm.html";
    }

    @GetMapping("/byRating")
    public String filmByRating(@RequestParam String voto, Model model) {
        model.addAttribute("listaFilm", serviceFilm.findFilmByRating(voto));
        return "adminPages/allFilm.html";
    }

    @GetMapping("/byAnno")
    public String filmByAnno(@RequestParam String anno, Model model) {
        model.addAttribute("listaFilm", serviceFilm.findFilmByAnno(anno));
        return "adminPages/allFilm.html";
    }

    @GetMapping("/byAttore")
    public String filmByAttore(@RequestParam String attore, Model model) {
        model.addAttribute("listaFilm", serviceFilm.findFilmByAttore(attore));
        return "adminPages/allFilm.html";
    }

    // ----- per ora non utilizzati -----

    @GetMapping("/tendenze")
    public String filmTendenze(@RequestParam String attore, Model model) {
        model.addAttribute("listaFilm", serviceFilm.find8());
        return "/home";
    }

    @GetMapping("/byUtente")
    public String allUtente(@RequestParam Long id, Model model) {
        model.addAttribute("listaFilm", serviceFilm.findFilmByUtente(id));
        return "filmUtente";
    }

    @GetMapping("/genereOneUtente")
    public String genereUtente(@RequestParam Long id, @RequestParam String genere, Model model) {
        model.addAttribute("listaFilm", serviceFilm.findFilmByGenereAndUtente(genere, id));
        return "filmUtente";
    }

    @GetMapping("/titoloOneUtente")
    public String titoloUtente(@RequestParam Long id, @RequestParam String titolo, Model model) {
        model.addAttribute("listaFilm", serviceFilm.findFilmByTitoloAndUtente(titolo, id));
        return "filmUtente";
    }

    @GetMapping("/ratingOneUtente")
    public String ratingUtente(@RequestParam Long id, @RequestParam String rating, Model model) {
        model.addAttribute("listaFilm", serviceFilm.findFilmByRatingAndUtente(rating, id));
        return "filmUtente";
    }

    @GetMapping("/annoOneUtente")
    public String annoUtente(@RequestParam Long id, @RequestParam String anno, Model model) {
        model.addAttribute("listaFilm", serviceFilm.findFilmByAnnoAndUtente(anno, id));
        return "filmUtente";
    }

    @GetMapping("/attoreOneUtente")
    public String attoreUtente(@RequestParam Long id, @RequestParam String attore, Model model) {
        model.addAttribute("listaFilm", serviceFilm.findFilmByAttoreAndUtente(attore, id));
        return "filmUtente";
    }
}
