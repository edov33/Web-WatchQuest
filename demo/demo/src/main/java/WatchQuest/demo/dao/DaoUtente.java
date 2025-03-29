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
    }

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
    }

    @Override
    public void delete(Long id) {
        String comando = "DELETE FROM utente WHERE id = ?";
        databaseMySql.executeDML(comando, String.valueOf(id));
    }

    // metodo che cerca se esiste già un utente
    public Utente cercaUtente(Map<String, String> datiForm) {
        Utente u = null;
        String query = "SELECT * FROM utente u WHERE nome = ? AND cognome = ? AND email = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, datiForm.get("nome"),
                datiForm.get("cognome"), datiForm.get("email"));
        if (result.size() == 1) {
            for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
                return context.getBean(Utente.class, coppia.getValue());
            }
        }
        return u;
    }

    // metodo che controlla se quello user esiste già
    public Utente readByUserAndPass(String username, String password) {
        String query = "SELECT u.id FROM utente u WHERE u.username = ? AND u.password = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, username, password);
        Long id = null;
        if (result.size() == 1) {
            id = Long.parseLong(new ArrayList<>(result.values()).get(0).get("id"));
        }
        return readById(id);
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

    @Override
    public Utente readById(Long id) {
        String query = "SELECT u.* FROM utente u WHERE id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(id));
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            return context.getBean(Utente.class, coppia.getValue());
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

    // metodo che filtra gli utenti per nominativo
    public Map<Long, GenericEntity> readUtenteByNominativo(String nome, String cognome) {
        String query = "SELECT u.* FROM utente u WHERE nome LIKE ? AND cognome LIKE ?";
        Map<Long, GenericEntity> ris = new HashMap<>();
        nome = (nome != null && !nome.isEmpty()) ? "%" + nome + "%" : "%";
        cognome = (cognome != null && !cognome.isEmpty()) ? "%" + cognome + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, nome, cognome);
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Utente.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che filtra gli utenti per nome
    public Map<Long, GenericEntity> readUtenteByNome(String nome) {
        String query = "SELECT u.* FROM utente u WHERE u.nome LIKE ?";
        nome = (nome != null && !nome.isEmpty()) ? "%" + nome + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, nome);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Utente.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che filtra gli utenti per cognome
    public Map<Long, GenericEntity> readUtenteByCognome(String cognome) {
        String query = "SELECT u.* FROM utente u WHERE u.cognome LIKE ?";
        cognome = (cognome != null && !cognome.isEmpty()) ? "%" + cognome + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, cognome);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Utente.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che filtra gli utenti per username
    public Map<Long, GenericEntity> readUtenteByUsername(String username) {
        String query = "SELECT u.* FROM utente u WHERE u.username LIKE ?";
        username = (username != null && !username.isEmpty()) ? "%" + username + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, username);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Utente.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che filtra gli utenti per email
    public Map<Long, GenericEntity> readUtenteByEmail(String email) {
        String query = "SELECT u.* FROM utente u WHERE u.email LIKE ?";
        email = (email != null && !email.isEmpty()) ? "%" + email + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, email);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Utente.class, coppia.getValue()));
        }
        return ris;
    }
}
