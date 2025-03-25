package WatchQuest.demo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.entity.Film;
import WatchQuest.demo.entity.GenericEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
public class DaoFilm extends DaoProgramma implements IDao<Long, Film> {

    private final ApplicationContext context;
    private final DatabaseMySql databaseMySql;

    @Override
    public Long create(Film e) {
        Long id = super.createProgramma(e);

        String query = "INSERT INTO Film (durata,id_programma) VALUES(?,?)";
        if(id != null){
            e.setId(id);
            databaseMySql.executeDML(query, String.valueOf(e.getDurata()), String.valueOf(id) );
        }
        return id;
    }

    @Override
    public Map<Long, GenericEntity> read() {
        String query = "SELECT * FROM Film JOIN programma ON Film.id_programma - programma.id";
        Map<Long,Map<String,String>> result = databaseMySql.executeDQL(query);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            
            Film f = context.getBean(Film.class, coppia.getValue());
            ris.put(coppia.getKey(), f); 
        }
        return ris;
    }

    @Override
    public void update(Film e) {
        super.updateProgramma(e);
        String query = "UPDATE Film SET durata = ? WHERE id = ?";
        databaseMySql.executeDML(query, String.valueOf(e.getDurata()),String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) { 
        super.delete(id);
    }

    @Override
    public Film readById(Long id) {
         String query = "SELECT * FROM Film JOIN programma ON Film.id_programma = programma.id WHERE Film.id_programma=?";
        Map<Long,Map<String,String>> result = databaseMySql.executeDQL(query, String.valueOf(id));
        if(result.size() == 1){
            for(Entry<Long, Map<String, String>> coppia : result.entrySet()){
                Film f = context.getBean(Film.class, coppia.getValue());
                return f;
            }
        }
        return null;
        
    }
    
}
