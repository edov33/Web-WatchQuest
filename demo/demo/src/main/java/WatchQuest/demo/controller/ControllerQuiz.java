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
@RequestMapping("/quiz")
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
        return "redirect:/quiz/all";
    }

    @PostMapping("/inserisci")
    public String inserisciQuiz(@RequestParam Map<String, String> parametri) {
        serviceQuiz.save(parametri);
        return "redirect:/quiz/all";
    }

    @GetMapping("/elimina")
    public String eliminaQuiz(@RequestParam(defaultValue = "0L") Long idQuiz) {
        if (idQuiz != 0) {
            serviceQuiz.delete(idQuiz);
        }
        return "redirect:/quiz/all";
    }

    @GetMapping("/byGenere")
    public String quizByGenere(@RequestParam String genere, Model model) {
        model.addAttribute("lista", serviceQuiz.findQuizByGenere(genere));
        return "quiz";
    }

    @GetMapping("/rand")
    public String quizRand(Model model) {
        model.addAttribute("lista", serviceQuiz.findQuizRandNum());
        return "quiz";
    }

    @GetMapping("/randByGenere")
    public String quizRandByGenere(@RequestParam String genere, Model model) {
        model.addAttribute("lista", serviceQuiz.findQuizRandByGenere(genere));
        return "quiz";
    }
    
    @GetMapping("/nuove")
    public String quizNuoviByUtente(@RequestParam Long idUtente, Model model) {
        model.addAttribute("lista", serviceQuiz.findQuizNuoviByUtente(idUtente));
        return "quiz";
    }
    
    @GetMapping("/nuovi")
    public String quizNuovi(@RequestParam Long idUtente, Model model) {
        model.addAttribute("lista", serviceQuiz.findQuizNuoviByUtente(idUtente));
        return "quiz";
    }
}
