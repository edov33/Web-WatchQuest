package WatchQuest.demo.dao;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.entity.Film;
import WatchQuest.demo.entity.GenericEntity;
import lombok.Data;

@Service
@Data
public class DaoFilm extends DaoProgramma implements IDao<Long, Film> {

    private final ApplicationContext context;
    private final DatabaseMySql databaseMySql;

    @Override
    public Long create(Film f) {
        Long id = super.createProgramma(f);

        String query = "INSERT INTO film (durata,id_programma) VALUES(?,?)";
        if (id != null) {
            return databaseMySql.executeDML(query, String.valueOf(f.getDurata()), String.valueOf(id));
        }
        return null;
    }

    @Override
    public Map<Long, GenericEntity> read() {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    @Override
    public void update(Film f) {
        super.updateProgramma(f);
        String query = "UPDATE film SET durata = ? WHERE id_programma = ?";
        databaseMySql.executeDML(query, String.valueOf(f.getDurata()), String.valueOf(f.getId()));
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }


    
    // sopra ok
    // sotto tentativi per visualizzare film in base all'utente

    
    @Override
    public Film readById(Long id) {
        String query = "SELECT * FROM film f JOIN programma p ON f.id_programma = p.id WHERE f.id_programma=?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(id));
        if (result.size() == 1) {
            for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
                return context.getBean(Film.class, coppia.getValue());
            }
        }
        return null;
    }

    public Map<Long, GenericEntity> readFilmByUtente(Long idUtente) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id JOIN utente_programma up ON p.id = up_id_programma JOIN utente u ON up.id_utente = u.id WHERE u.id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    
}