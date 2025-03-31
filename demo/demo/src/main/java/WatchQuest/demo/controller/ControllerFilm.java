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
import jakarta.servlet.http.HttpSession;

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

    public boolean isAdmin(HttpSession session) {
        if (session.getAttribute("loggato") != null) {
            if (session.getAttribute("ruolo").equals("admin")) {
                return true;
            }
        }
        return false;
    }

    // pagina admin
    @GetMapping("/byTitolo")
    public String filmByTitolo(@RequestParam String titolo, Model model, HttpSession session) {
        if (isAdmin(session)) {
            model.addAttribute("listaFilm", serviceFilm.findFilmByTitolo(titolo));
            fotoETasti(model, session);
            return "adminPages/allFilm.html";
        }
        return "redirect:/home";
    }

    @GetMapping("/byGenere")
    public String filmByGenere(@RequestParam String genere, Model model, HttpSession session) {
        if (isAdmin(session)) {
            model.addAttribute("listaFilm", serviceFilm.findFilmByGenere(genere));
            fotoETasti(model, session);
            return "adminPages/allFilm.html";
        }
        return "redirect:/home";
    }

    @GetMapping("/byRating")
    public String filmByRating(@RequestParam String voto, Model model, HttpSession session) {
        if (isAdmin(session)) {
            model.addAttribute("listaFilm", serviceFilm.findFilmByRating(voto));
            fotoETasti(model, session);
            return "adminPages/allFilm.html";
        }
        return "redirect:/home";
    }

    @GetMapping("/byAnno")
    public String filmByAnno(@RequestParam String anno, Model model, HttpSession session) {
        if (isAdmin(session)) {
            model.addAttribute("listaFilm", serviceFilm.findFilmByAnno(anno));
            fotoETasti(model, session);
            return "adminPages/allFilm.html";
        }
        return "redirect:/home";
    }

    @GetMapping("/byAttore")
    public String filmByAttore(@RequestParam String attore, Model model, HttpSession session) {
        if (isAdmin(session)) {
            model.addAttribute("listaFilm", serviceFilm.findFilmByAttore(attore));
            fotoETasti(model, session);
            return "adminPages/allFilm.html";
        }
        return "redirect:/home";
    }
    // fine pagina admin

    // pagina ricerca
    @GetMapping("/Titolo")
    public String filmTitolo(@RequestParam String titolo, Model model, HttpSession session) {
            model.addAttribute("listaFilm", serviceFilm.findFilmByTitolo(titolo));
            fotoETasti(model, session);
            return "ricercaFilm";
    }
    
    @GetMapping("/Genere")
    public String filmGenere(@RequestParam String genere, Model model, HttpSession session) {
            model.addAttribute("listaFilm", serviceFilm.findFilmByGenere(genere));
            fotoETasti(model, session);
            return "ricercaFilm";
    }
    
    @GetMapping("/Rating")
    public String filmRating(@RequestParam String voto, Model model, HttpSession session) {
            model.addAttribute("listaFilm", serviceFilm.findFilmByRating(voto));
            fotoETasti(model, session);
            return "ricercaFilm";
    }
    
    @GetMapping("/Anno")
    public String filmAnno(@RequestParam String anno, Model model, HttpSession session) {
            model.addAttribute("listaFilm", serviceFilm.findFilmByAnno(anno));
            return "ricercaFilm";
    }
    
    @GetMapping("/Attore")
    public String filmAttore(@RequestParam String attore, Model model, HttpSession session) {
            model.addAttribute("listaFilm", serviceFilm.findFilmByAttore(attore));
            fotoETasti(model, session);
            return "ricercaFilm";
    }
    // fine pagina ricerca

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
