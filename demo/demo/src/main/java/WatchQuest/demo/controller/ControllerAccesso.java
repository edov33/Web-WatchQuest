package WatchQuest.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import WatchQuest.demo.entity.Utente;
import WatchQuest.demo.service.ServiceUtente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class ControllerAccesso {

    @Autowired
    private ServiceUtente serviceUtente;

    @PostMapping("/registrazione")
    public String register(@RequestParam Map<String, String> datiForm, Model model) {
        Utente u = serviceUtente.findByUserAndPass(datiForm.get("username"), datiForm.get("password"));
        if (!datiForm.get("password").equals(datiForm.get("confermapassword"))) {
            model.addAttribute("error", "le password non coincidono");
            // return "redirect:/registrazione";
            return "errore";
        }
        // se u = null non ho un utente con quello username e password
        if (u == null) {
            u = serviceUtente.findUtente(datiForm);
            // se u = null non ho un utente con quel nome cognome e email
            if (u == null) {
                serviceUtente.save(datiForm);
                return "redirect:/utente/profilo";
            } else {
                // ho già quel trio insieme
                model.addAttribute("error", "nome cognome email già inserita");
                // return "redirect:/registrazione";
                return "errore";
            }
        } else {
            // ho già username e password
            model.addAttribute("error", "username e password già in uso");
            // return "redirect:/registrazione";
            return "errore";
        }
    }

    @PostMapping("/controllo-sessione")
    public String controllo(Model model, HttpSession session) {
        model.addAttribute("loggato", session.getId());
        return "";
    }

    @PostMapping("/attempt")
    public String login(@RequestParam String username, @RequestParam String password, Model model,
            HttpSession session) {

        // devo fare il login
        // controllo se username e password esistono
        Utente u = serviceUtente.findByUserAndPass(username, password);
        // System.out.println("\n--------------------utente-------------------\n" + u);
        if (u == null) {
            // System.out.println("\n-------------------u null------------------\n");
            // -se non esistono messaggio di errore e reindirizziamo all'homepage
            model.addAttribute("error", "Credenziali non valide");
            return "errore";
        } else if (username.equals("admin") && password.equals("admin")) {
            // admin
            session.setAttribute("ruolo", "admin");
            session.setAttribute("loggato", "ok");
            model.addAttribute("loggato", session.getId());
        } else {
            // utente
            session.setAttribute("ruolo", "utente");
            session.setAttribute("utente", u);
            session.setAttribute("loggato", "ok");
            model.addAttribute("loggato", session.getId());
        }

        // System.out.println("\n-------sto entrato come " +
        // session.getAttribute("ruolo") + "--------------\n");
        return "home";
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);

        if (session == null) {
            // valid session doesn't exist
            // do something like send the user to a login screen
        }

        if (session.getAttribute("username") == null) {
            // no username in session
            // user probably hasn't logged in properly
        }

        // now let's pretend to log the user out for good measure
        session.invalidate();
    }
}
