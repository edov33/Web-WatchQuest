package WatchQuest.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Quiz extends GenericEntity {

    private String domanda;
    private String risposta;
    private String genere;
    
}
