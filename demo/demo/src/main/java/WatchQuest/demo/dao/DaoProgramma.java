package WatchQuest.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.entity.Programma;
import lombok.Data;

@Data
@Service
public abstract class DaoProgramma {
    @Autowired
    private DatabaseMySql databaseMySql;

    public Long createProgramma(Programma e){
        String query = "INSERT INTO persone (titolo,descrizione,genere,anno_pubblicazione,classificazione,rating,cast,regista,lingua_originale) values(?,?,?,?,?,?,?,?,?)";
        return databaseMySql.executeDML(query, e.getTitolo(), e.getDescrizione(),e.getGenere(),String.valueOf(e.getAnnoPubblicazione()), e.getClassificazione(), String.valueOf(e.getRating()), 
                                            e.getCast(), e.getRegista(), e.getLinguaOriginale() );
    }
    
    public void updateProgramma(Programma e){
        String queryPersona = "UPDATE Programma SET titolo=?, descrizione=?, genere=?, anno_pubblicazione=?, classificazione=?, rating=?, cast=?, regista=?, lingua_originale=?";
        databaseMySql.executeDML(queryPersona, e.getTitolo(), e.getDescrizione(), e.getGenere(), String.valueOf(e.getAnnoPubblicazione()), e.getClassificazione(), String.valueOf(e.getRating()), 
                                    e.getCast(), e.getRegista(), e.getLinguaOriginale(), String.valueOf(e.getId()));
    }
    
    public void delete(Long id){
        String query = "DELETE FROM Programma WHERE id = ?";
        databaseMySql.executeDML(query, String.valueOf(id));
    }
}
