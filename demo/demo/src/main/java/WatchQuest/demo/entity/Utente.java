package WatchQuest.demo.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Utente extends GenericEntity{
    
    private String nome, cognome, username;
    private LocalDate data_nascita;
    private String password;
    private String email;
    private String lingua;
    private boolean abbonamento;
    private List<Quiz> quiz;
    private List<Programma> programma;
    
}
