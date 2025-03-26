package WatchQuest.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import WatchQuest.demo.service.ServiceSerie;

@Controller
@RequestMapping("/serie")
public class ControllerSerie {

    @Autowired
    private ServiceSerie serviceSerie;

    @GetMapping("/all")
    public String allSerie(Model model) {
        model.addAttribute("lista", serviceSerie.findAll());
        return "serie";
    }

    @PostMapping("/modifica")
    public String modificaSerie(@RequestParam Map<String, String> parametri) {
        serviceSerie.update(parametri);
        return "redirect:/home";
    }

    @PostMapping("/inserisci")
    public String inserisciSerie(@RequestParam Map<String, String> parametri) {
        serviceSerie.save(parametri);
        return "redirect:/home";
    }

    @GetMapping("/elimina")
    public String eliminaSerie(@RequestParam(defaultValue = "0L") Long idSerie) {
        if (idSerie != 0) {
            serviceSerie.delete(idSerie);
        }
        return "redirect:/home";
    }

}
