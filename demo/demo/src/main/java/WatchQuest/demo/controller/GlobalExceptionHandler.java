package WatchQuest.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* */
@ControllerAdvice
public class GlobalExceptionHandler {

    // Gestisce tutte le eccezioni di tipo NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException ex, Model model) {
        // Aggiunge un messaggio di errore nel model
        model.addAttribute("error", "Si è verificato un errore: la risorsa non esiste " + ex.getMessage() );
        return "paginaErrore"; // Restituisce il nome della pagina di errore
    }
    
    //per qualsiasi altra eccezione generica o personalizzata
     // Puoi gestire altre eccezioni in modo simile
     @ExceptionHandler(Exception.class)
     public String handleException(Exception ex) {
         return "redirect:/home";  // Reindirizza a /login per qualsiasi altra eccezione
     }
}
