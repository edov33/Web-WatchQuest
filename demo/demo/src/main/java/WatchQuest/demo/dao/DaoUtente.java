package WatchQuest.demo.dao;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.entity.GenericEntity;
import WatchQuest.demo.entity.Quiz;
import WatchQuest.demo.entity.Utente;
import lombok.Data;

@Data
@Service
public class DaoUtente implements IDao<Long, Utente> {

    private final DatabaseMySql databaseMySql;
    private final ApplicationContext context;
    // private final DaoSerie daoSerie;
    private final DaoFilm daoFilm;

    @Override
    public Long create(Utente u) {
        String comando = "INSERT INTO utente nome, cognome, data_nascita, username, password, email, lingua, abbonamento VALUES (?,?,?,?,?,?,?)";
        return databaseMySql.executeDML(comando, u.getNome(), u.getCognome(), String.valueOf(u.getData_nascita()),
                u.getUsername(), u.getPassword(), u.getEmail(), u.getLingua(), String.valueOf(u.isAbbonamento()));
    }

    @Override
    public Map<Long, GenericEntity> read() {
        String query = "SELECT * FROM utente";
        Map<Long, GenericEntity> ris = new HashMap<>();
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query);
        for (Entry<Long, Map<String,String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Utente.class, coppia.getValue()));            
        }
        return ris;
    }

    @Override
    public void update(Utente u) {
        String comando = "UPDATE utente SET nome = ?, cognome = ?, data_nascita = ?, username = ?, password = ?, email = ?, lingua = ?, abbonamento = ?";
        databaseMySql.executeDML(comando, u.getNome(), u.getCognome(), String.valueOf(u.getData_nascita()),
                u.getUsername(), u.getPassword(), u.getEmail(), u.getLingua(), String.valueOf(u.isAbbonamento()));
    }

    @Override
    public void delete(Long id) {
        String comando = "DELETE FROM utente WHERE id = ?";
        databaseMySql.executeDML(comando, String.valueOf(id));
    }

    @Override
    public Utente readById(Long id) {
        String query = "SELECT * FROM utente WHERE id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(id));
        for (Entry<Long,Map<String,String>> coppia : result.entrySet()) {
            return context.getBean(Utente.class, coppia);
        }
        return null;
    }

}
