package WatchQuest.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Prodotto {

    private String titolo;
    private String descrizione;
    private String genere;
    private int annoPubblicazione;
    private String classificazione;
    private double rating;
    private String regista;
    private String linguaOriginale;
}
