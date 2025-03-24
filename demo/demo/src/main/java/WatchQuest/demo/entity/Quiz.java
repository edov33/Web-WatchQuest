package WatchQuest.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Quiz extends GenericEntity {

    private String domanda;
    private String risposta;
    // private double punti_fatti; in valutazione
    private double punteggio;// per la domanda
    private boolean giaFatte;
    private Programma programma;
    private Utente utente;

}
