package WatchQuest.demo.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import WatchQuest.demo.service.ServiceQuiz;

@Controller
@RequestMapping("quiz")
public class ControllerQuiz {
        
    @Autowired
    private ServiceQuiz serviceQuiz;

    @GetMapping("/all")
    public String allQuiz(Model model) {
        model.addAttribute("lista", serviceQuiz.findAll());
        return "quiz";
    }

    @PostMapping("/modifica")
    public String modificaQuiz(@RequestParam Map<String, String> parametri) {
        serviceQuiz.update(parametri);
        return "redirect:/home";
    }

    @PostMapping("/inserisci")
    public String inserisciQuiz(@RequestParam Map<String, String> parametri) {
        serviceQuiz.save(parametri);
        return "redirect:/home";
    }

    @GetMapping("/elimina")
    public String eliminaQuiz(@RequestParam(defaultValue = "0L") Long idQuiz) {
        if (idQuiz != 0) {
            serviceQuiz.delete(idQuiz);
        }
        return "redirect:/home";
    }
}
