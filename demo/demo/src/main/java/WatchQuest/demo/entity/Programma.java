package WatchQuest.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Programma extends GenericEntity{

    private String titolo;
    private String descrizione;
    private String genere;
    private int anno_pubblicazione;
    private String classificazione;
    private String cast;
    private double rating;
    private String regista;
    private String lingua_originale;
    private String url;
}
