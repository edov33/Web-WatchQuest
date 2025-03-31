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
import WatchQuest.demo.service.ServiceSerie;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/serie")
public class ControllerSerie {

    @Autowired
    private ServiceSerie serviceSerie;

    @GetMapping("/all")
    public String allSerie(Model model) {
        model.addAttribute("listaSerie", serviceSerie.findAll());
        return "adminPages/allSerie.html";
    }

    @PostMapping("/modifica")
    public String modificaSerie(@RequestParam Map<String, String> parametri) {
        serviceSerie.update(parametri);
        return "redirect:/serie/all";
    }

    @PostMapping("/inserisci")
    public String inserisciSerie(@RequestParam Map<String, String> parametri) {
        serviceSerie.save(parametri);
        return "redirect:/serie/all";
    }

    @GetMapping("/elimina")
    public String eliminaSerie(@RequestParam(defaultValue = "0L") Long idSerie) {
        if (idSerie != 0) {
            serviceSerie.delete(idSerie);
        }
        return "redirect:/serie/all";
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
    public String serieByTitolo(@RequestParam String titolo, Model model, HttpSession session) {
        if (isAdmin(session)) {
            model.addAttribute("listaSerie", serviceSerie.findSerieByTitolo(titolo));
            fotoETasti(model, session);
            return "adminPages/allSerie.html";
        }
        return "redirect:/home";
    }

    @GetMapping("/byGenere")
    public String serieByGenere(@RequestParam String genere, Model model, HttpSession session) {
        if (isAdmin(session)) {
            model.addAttribute("listaSerie", serviceSerie.findSerieByGenere(genere));
            fotoETasti(model, session);
            return "adminPages/allSerie.html";
        }
        return "redirect:/home";
    }

    @GetMapping("/byRating")
    public String serieByRating(@RequestParam String voto, Model model, HttpSession session) {
        if (isAdmin(session)) {
            model.addAttribute("listaSerie", serviceSerie.findSerieByRating(voto));
            fotoETasti(model, session);
            return "adminPages/allSerie.html";
        }
        return "redirect:/home";
    }

    @GetMapping("/byAnno")
    public String serieByAnno(@RequestParam String anno, Model model, HttpSession session) {
        if (isAdmin(session)) {
            model.addAttribute("listaSerie", serviceSerie.findSerieByAnno(anno));
            fotoETasti(model, session);
            return "adminPages/allSerie.html";
        }
        return "redirect:/home";
    }

    @GetMapping("/byAttore")
    public String serieByAttore(@RequestParam String attore, Model model, HttpSession session) {
        if (isAdmin(session)) {
            model.addAttribute("listaSerie", serviceSerie.findSerieByAttore(attore));
            fotoETasti(model, session);
            return "adminPages/allSerie.html";
        }
        return "redirect:/home";
    }
    // fine pagina admin

    // pagina ricerca
    @GetMapping("/Titolo")
    public String serieTitolo(@RequestParam String titolo, Model model, HttpSession session) {
        fotoETasti(model, session);
        model.addAttribute("listaSerie", serviceSerie.findSerieByTitolo(titolo));
        return "ricercaSerie";
    }

    @GetMapping("/Genere")
    public String serieGenere(@RequestParam String genere, Model model, HttpSession session) {
        fotoETasti(model, session);
        model.addAttribute("listaSerie", serviceSerie.findSerieByGenere(genere));
        return "ricercaSerie";
    }

    @GetMapping("/Rating")
    public String serieRating(@RequestParam String voto, Model model, HttpSession session) {
        fotoETasti(model, session);
        model.addAttribute("listaSerie", serviceSerie.findSerieByRating(voto));
        return "ricercaSerie";
    }

    @GetMapping("/Anno")
    public String serieAnno(@RequestParam String anno, Model model, HttpSession session) {
        fotoETasti(model, session);
        model.addAttribute("listaSerie", serviceSerie.findSerieByAnno(anno));
        return "ricercaSerie";
    }

    @GetMapping("/Attore")
    public String serieAttore(@RequestParam String attore, Model model, HttpSession session) {
        model.addAttribute("listaSerie", serviceSerie.findSerieByAttore(attore));
        fotoETasti(model, session);
        return "ricercaSerie";
    }
    // fine pagina ricerca

    // ----- per ora non utilizzati -----
    @GetMapping("/tendenze")
    public String serieTendenze(@RequestParam String attore, Model model) {
        model.addAttribute("listaSerie", serviceSerie.find8());
        return "/home";
    }

    @GetMapping("/genereOneUtente")
    public String genereUtente(@RequestParam Long id, @RequestParam String genere, Model model) {
        model.addAttribute("listaSerie", serviceSerie.findSerieByGenereAndUtente(genere, id));
        return "serieUtente";
    }

    @GetMapping("/titoloOneUtente")
    public String titoloUtente(@RequestParam Long id, @RequestParam String titolo, Model model) {
        model.addAttribute("listaSerie", serviceSerie.findSerieByTitoloAndUtente(titolo, id));
        return "serieUtente";
    }

    @GetMapping("/ratingOneUtente")
    public String ratingUtente(@RequestParam Long id, @RequestParam String rating, Model model) {
        model.addAttribute("listaSerie", serviceSerie.findSerieByRatingAndUtente(rating, id));
        return "serieUtente";
    }

    @GetMapping("/annoOneUtente")
    public String annoUtente(@RequestParam Long id, @RequestParam String anno, Model model) {
        model.addAttribute("listaSerie", serviceSerie.findSerieByAnnoAndUtente(anno, id));
        return "serieUtente";
    }

    @GetMapping("/attoreOneUtente")
    public String attoreUtente(@RequestParam Long id, @RequestParam String attore, Model model) {
        model.addAttribute("listaSerie", serviceSerie.findSerieByAttoreAndUtente(attore, id));
        return "serieUtente";
    }

}
