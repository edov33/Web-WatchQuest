package WatchQuest.demo.entity;

import java.time.LocalDate;

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
    
}
