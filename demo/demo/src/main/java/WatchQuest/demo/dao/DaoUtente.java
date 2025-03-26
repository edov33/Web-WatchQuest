package WatchQuest.demo.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.entity.Film;
import WatchQuest.demo.entity.GenericEntity;
import WatchQuest.demo.entity.Utente;
import lombok.Data;

@Data
@Service
public class DaoUtente implements IDao<Long, Utente> {

    private final DatabaseMySql databaseMySql;
    private final ApplicationContext context;

    @Override
    public Long create(Utente u) {
        String comando = "INSERT INTO utente (nome, cognome, data_nascita, username, password, email, lingua) VALUES (?,?,?,?,?,?,?)";
        return databaseMySql.executeDML(comando, u.getNome(), u.getCognome(), String.valueOf(u.getData_nascita()),
                u.getUsername(), u.getPassword(), u.getEmail(), u.getLingua());
    }// , abbonamento, ?, String.valueOf(u.isAbbonamento())

    @Override
    public Map<Long, GenericEntity> read() {
        String query = "SELECT * FROM utente";
        Map<Long, GenericEntity> ris = new HashMap<>();
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query);
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Utente.class, coppia.getValue()));
        }
        return ris;
    }

    @Override
    public void update(Utente u) {
        String comando = "UPDATE utente SET nome = ?, cognome = ?, data_nascita = ?, username = ?, password = ?, email = ?, lingua = ? WHERE id = ?";
        databaseMySql.executeDML(comando, u.getNome(), u.getCognome(), String.valueOf(u.getData_nascita()),
                u.getUsername(), u.getPassword(), u.getEmail(), u.getLingua(), String.valueOf(u.getId()));
    }// , abbonamento = ? , String.valueOf(u.isAbbonamento())

    @Override
    public void delete(Long id) {
        String comando = "DELETE FROM utente WHERE id = ?";
        databaseMySql.executeDML(comando, String.valueOf(id));
    }

    // associazione utente - programma
    public void associaProgramma(Long idUtente, Long idProgramma) {
        String query = "INSERT INTO utente_progrmma (id_utente, id_programma) VALUES(?,?)";
        databaseMySql.executeDML(query, String.valueOf(idUtente), String.valueOf(idProgramma));
    }

    // dissociazione utente - programma
    public void dissociaProgramma(Long idUtente, Long idProgramma) {
        String query = "DELETE FROM utente_progrmma WHERE id_utente = ? AND id_programma = ?";
        databaseMySql.executeDML(query, String.valueOf(idUtente), String.valueOf(idProgramma));
    }

    // associazione utente - quiz
    public void associaQuiz(Long idUtente, Long idQuiz) {
        String query = "INSERT INTO utente_quiz (id_utente, id_quiz) VALUES(?,?)";
        databaseMySql.executeDML(query, String.valueOf(idUtente), String.valueOf(idQuiz));
    }

    // dissociazione utente - quiz
    public void dissociaQuiz(Long idUtente, Long idQuiz) {
        String query = "DELETE FROM utente_quiz WHERE id_utente = ? AND id_quiz = ?";
        databaseMySql.executeDML(query, String.valueOf(idUtente), String.valueOf(idQuiz));
    }

    // sopra ok
    // sotto tentativi per visualizzare film in base all'utente

    @Override
    public Utente readById(Long id) {
        String query = "SELECT * FROM utente WHERE id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(id));
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            return context.getBean(Utente.class, coppia);
        }
        return null;
    }

    // metodo che restituisce i film associati ad un utente
    public Map<Long, GenericEntity> readProgrammaByIdUtente(Long idUtente) {
        String query = "SELECT u.*, p.titolo FROM utente u JOIN utente_programma up ON u.id = up.id_utente " +
                "JOIN programma p ON up.id_programma = p.id WHERE up.id_utente = ?";
        Map<Long, GenericEntity> ris = new HashMap<>();
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(idUtente));
        GenericEntity e = null;
        for (Map<String, String> entita : result.values()) {
            e = context.getBean(Film.class, entita);
            if (e != null) {
                ris.put(e.getId(), e);
            }
        }
        return ris;
    }
}
