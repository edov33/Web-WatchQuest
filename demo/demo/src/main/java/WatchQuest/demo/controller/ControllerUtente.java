package WatchQuest.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import WatchQuest.demo.service.ServiceUtente;

@Controller
@RequestMapping("/utente")
public class ControllerUtente {

    @Autowired
    private ServiceUtente serviceUtente;

    @GetMapping("/all")
    public String allUtente(Model model) {
        model.addAttribute("lista", serviceUtente.findAll());
        return "user";
    }

    @PostMapping("/modifica")
    public String modificaUtente(@RequestParam Map<String, String> parametri) {
        serviceUtente.update(parametri);
        return "redirect:/utente/all";
    }

    @PostMapping("/inserisci")
    public String inserisciUtente(@RequestParam Map<String, String> parametri) {
        serviceUtente.save(parametri);
        return "redirect:/utente/all";
    }

    @GetMapping("/elimina")
    public String eliminaUtente(@RequestParam(defaultValue = "0L") Long idUtente) {
        if (idUtente != 0) {
            serviceUtente.delete(idUtente);
        }
        return "redirect:/utente/all";
    }

    

    @GetMapping("/profilo")
    public String profilo(Model model) {
        // model.addAttribute("lista", serviceUtente.findAllProgrammiUtente());
        return "home";
    }

    @GetMapping("/filmUtente")
    public String allFilmUtente(Model model) {
        model.addAttribute("lista", serviceUtente.findAllProgrammiUtente());
        return "filmOneUtente";
    }

    @GetMapping("/serieUtente")
    public String allSerieUtente(Model model) {
        model.addAttribute("lista", serviceUtente.findAllProgrammiUtente());
        return "serieOneUtente";
    }

    @GetMapping("/byNome")
    public String utenteByNome(@RequestParam String nome, Model model) {
        model.addAttribute("lista", serviceUtente.findUtenteByNome(nome));
        return "user";
    }

    @GetMapping("/byCognome")
    public String utenteByCognome(@RequestParam String cognome, Model model) {
        model.addAttribute("lista", serviceUtente.findUtenteByCognome(cognome));
        return "user";
    }

    @GetMapping("/byNominativo")
    public String utenteByNominativo(@RequestParam String nome, @RequestParam String cognome, Model model) {
        model.addAttribute("lista", serviceUtente.findUtenteByNominativo(nome, cognome));
        return "user";
    }

    @GetMapping("/byUsername")
    public String utenteByUsername(@RequestParam String username, Model model) {
        model.addAttribute("lista", serviceUtente.findUtenteByUsername(username));
        return "user";
    }

    @GetMapping("/byEmail")
    public String utenteByEmail(@RequestParam String email, Model model) {
        model.addAttribute("lista", serviceUtente.findUtenteByEmail(email));
        return "user";
    }

    // -------------------------------------------
    @GetMapping("/dettagliUtente")
    public String dettagliUtente(@RequestParam Long idUtente, Model model) {
        // Utente u = serviceUtente.findById(idUtente);
        // model.addAttribute("utente", u);
        model.addAttribute("lista", serviceUtente.findById(idUtente));
        return "dettagliUtente";
    }

    @GetMapping("/filmDettagliUtente")
    public String allFilmUnUtente(@RequestParam Long idUtente, Model model) {
        model.addAttribute("lista", serviceUtente.findAllProgrammiUnUtente(idUtente));
        return "filmOneUtente";
    }
}
