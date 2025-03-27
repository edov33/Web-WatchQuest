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

    @Override
    public Long create(Quiz q) {
        String comando = "INSERT INTO quiz (domanda, risposta, genere) VALUES (?, ?, ?)";
        return databaseMySql.executeDML(comando, q.getDomanda(), q.getRisposta(), q.getGenere());
    }

    @Override
    public Map<Long, GenericEntity> read() {
        String query = "SELECT * FROM quiz";
        Map<Long, GenericEntity> ris = new HashMap<>();
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query);
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Quiz.class, coppia.getValue()));
        }
        return ris;

    }

    @Override
    public void update(Quiz q) {
        String comando = "UPDATE quiz SET domanda = ?, risposta = ?, genere = ? WHERE id = ?";
        databaseMySql.executeDML(comando, q.getDomanda(), q.getRisposta(), q.getGenere(), String.valueOf(q.getId()));
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
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            return context.getBean(Quiz.class, coppia);
        }
        return null;
    }

    // metodo che restituisce un numero di domande casuali
    public Map<Long, GenericEntity> readQuizRandNum() {
        // TODO: selezionare il numero di domande e metterlo dopo LIMIT
        String query = "SELECT * FROM quiz ORDER BY rand() LIMIT 5";
        Map<Long, GenericEntity> ris = new HashMap<>();
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query);
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Quiz.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce i quiz in base al genere
    public Map<Long, GenericEntity> readQuizByGenere(String genere) {
        String query = "SELECT * FROM quiz WHERE genere LIKE ?";
        genere = (genere != null && !genere.isEmpty()) ? "%" + genere + "%" : "%";
        Map<Long, GenericEntity> ris = new HashMap<>();
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, genere);
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Quiz.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce un numero di domande casuali dello stesso genere
    public Map<Long, GenericEntity> readQuizRandByGenere(String genere) {
        // TODO: selezionare il numero di domande e metterlo dopo LIMIT
        String query = "SELECT * FROM quiz WHERE genere LIKE ? ORDER BY rand() LIMIT 2";
        genere = (genere != null && !genere.isEmpty()) ? "%" + genere + "%" : "%";
        Map<Long, GenericEntity> ris = new HashMap<>();
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, genere);
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Quiz.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce tutte le domande non fatte da un utente
    public Map<Long, GenericEntity> readQuizNuoviByUtente(Long idUtente) {
        String query = "SELECT * FROM quiz qu WHERE qu.id <> ALL (SELECT * FROM (SELECT q.id FROM utente u JOIN utente_quiz uq ON u.id = uq.id_utente JOIN quiz q ON uq.id_quiz = q.id WHERE u.id = ?) domande)";
        Map<Long, GenericEntity> ris = new HashMap<>();
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(idUtente));
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Quiz.class, coppia.getValue()));
        }
        return ris;
    }

}
