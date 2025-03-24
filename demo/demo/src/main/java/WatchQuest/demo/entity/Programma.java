package WatchQuest.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Programma {

    private String titolo;
    private String descrizione;
    private String genere;
    private int annoPubblicazione;
    private String classificazione;
    private String cast;
    private double rating;
    private String regista;
    private String linguaOriginale;
}
