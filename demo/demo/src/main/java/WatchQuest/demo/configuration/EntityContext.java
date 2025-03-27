package WatchQuest.demo.configuration;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import WatchQuest.demo.entity.Film;
import WatchQuest.demo.entity.Quiz;
import WatchQuest.demo.entity.Serie;
import WatchQuest.demo.entity.Utente;

@Configuration
public class EntityContext {

    @Bean
    @Scope("prototype")
    public Utente utente(Map<String, String> mappa) {
        Utente utente = new Utente();
        utente.fromMap(mappa);
        return utente;
    }
    
    @Bean
    @Scope("prototype")
    public Quiz quiz(Map<String, String> mappa) {
        Quiz quiz = new Quiz();
        quiz.fromMap(mappa);
        return quiz;
    }
    
    @Bean
    @Scope("prototype")
    public Film film(Map<String, String> mappa) {
        Film film = new Film();
        film.fromMap(mappa);
        return film;
    }

    @Bean
    @Scope("prototype")
    public Serie serie(Map<String, String> mappa) {
        Serie serieTv = new Serie();
        serieTv.fromMap(mappa);
        return serieTv;
    }
}