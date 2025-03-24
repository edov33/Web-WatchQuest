package WatchQuest.demo.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.entity.GenericEntity;
import WatchQuest.demo.entity.Quiz;
import lombok.Data;

@Service
@Data
public class DaoQuiz implements IDao<Long, Quiz> {

    private final DatabaseMySql databaseMySql;
    private final ApplicationContext context;

    // TODO: scegliere dove controllare id_programma
    @Override
    public Long create(Quiz q) {
        String comando = "INSERT INTO quiz (domanda, opzione_giusta, punteggio, usata) VALUES (?, ?, ? ,?, ?)";
        return databaseMySql.executeDML(comando, q.getDomanda(), q.getRisposta(),
                String.valueOf(q.getProgramma().getId()), String.valueOf(q.getPunteggio()),
                String.valueOf(q.isGiaFatte()));
    }

    @Override
    public Map<Long, GenericEntity> read() {
        String query = "SELECT * FROM quiz";
        Map<Long, GenericEntity> ris = new HashMap<>();
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query);
        for (Entry<Long,Map<String,String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Quiz.class, coppia.getValue()));
        }
        return ris;

    }

    @Override
    public void update(Quiz q) {
        String comando = "UPDATE quiz SET domanda = ?, opzione_giusta = ?, id_programma = ?,"
                + " punteggio = ?, usata  = ?";
        databaseMySql.executeDML(comando, q.getDomanda(), q.getRisposta(), String.valueOf(q.getProgramma().getId()),
                String.valueOf(q.getPunteggio()), String.valueOf(q.isGiaFatte()));
    }

    @Override
    public void delete(Long id) {
        String comando = "DELETE FROM quiz WHERE id = ?";
        databaseMySql.executeDML(comando, String.valueOf(id));
    }

    @Override
    public Quiz readById(Long id) {
        String query = "SELECT * FROM quiz WHERE id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(id));
        for (Entry<Long,Map<String,String>> coppia : result.entrySet()) {
            Quiz q = context.getBean(Quiz.class, coppia);
            return q;
        }
        return null;
    }

}
