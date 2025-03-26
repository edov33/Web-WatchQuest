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
        return "utente";
    }
    
    @PostMapping("/modifica")
    public String modificaUtente(@RequestParam Map<String, String> parametri){
        serviceUtente.update(parametri);
        return "redirect:/home";
    }
    
    @PostMapping("/inserisci")
    public String inserisciUtente(@RequestParam Map<String, String> parametri){
        serviceUtente.save(parametri);
        return "redirect:/home";
    }

    @GetMapping("/elimina")
    public String eliminaUtente(@RequestParam(defaultValue = "0L") Long idUtente) {
        if (idUtente != 0) {
            serviceUtente.delete(idUtente);
        }
        return "redirect:/home";
    }
}
