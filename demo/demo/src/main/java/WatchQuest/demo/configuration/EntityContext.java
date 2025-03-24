package WatchQuest.demo.configuration;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import WatchQuest.demo.entity.Film;
import WatchQuest.demo.entity.Programma;
import WatchQuest.demo.entity.Quiz;
import WatchQuest.demo.entity.SerieTv;
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
        // if(mappa.containsKey("id_programma")){
        //     quiz.setProgramma(new Programma());
        //     quiz.getProgramma().setId(Long.parseLong(mappa.get("id_programma")));
        // }
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
    public SerieTv serie(Map<String, String> mappa) {
        SerieTv serieTv = new SerieTv();
        serieTv.fromMap(mappa);
        return serieTv;
    }
}
