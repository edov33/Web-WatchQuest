package WatchQuest.demo.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.entity.GenericEntity;
import WatchQuest.demo.entity.Serie;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Service
@Data
@EqualsAndHashCode(callSuper = true)
public class DaoSerie extends DaoProgramma implements IDao<Long, Serie> {

    private final DatabaseMySql databaseMySql;
    private final ApplicationContext context;

    @Override
    public Long create(Serie s) {
        Long id = super.createProgramma(s);
        String query = "INSERT INTO serie (stagioni, episodi, id_programma) values(?,?,?)";
        if (id != null) {
            return databaseMySql.executeDML(query, String.valueOf(s.getStagioni()), String.valueOf(s.getEpisodi()),
                    String.valueOf(id));
        }
        return null;
    }

    @Override
    public Map<Long, GenericEntity> read() {
        String query = "SELECT p.*, s.stagioni, s.episodi FROM serie s JOIN programma p ON s.id_programma = p.id ORDER BY p.titolo ASC";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query);
        Map<Long, GenericEntity> ris = new LinkedHashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    @Override
    public void update(Serie s) {
        super.updateProgramma(s);
        String query = "UPDATE serie SET stagioni=?, episodi=? WHERE id_programma=?";
        databaseMySql.executeDML(query, String.valueOf(s.getStagioni()), String.valueOf(s.getEpisodi()),
                String.valueOf(s.getId()));
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public Serie readById(Long id) {
        String query = "SELECT * FROM serie WHERE id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(id));
        if (result.size() == 1) {
            for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
                return context.getBean(Serie.class, coppia);
            }
        }
        return null;
    }

    // metodo che restituisce le serie viste da un utente
    public Map<Long, GenericEntity> readSerieByUtente(Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id "
                + "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id "
                + "WHERE u.id = ?"; 
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce le serie filtrate per titolo
    public Map<Long, GenericEntity> readSerieByTitolo(String titolo) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id"
                + " WHERE p.titolo LIKE ?";
        titolo = (titolo != null && !titolo.isEmpty()) ? "%" + titolo + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, titolo);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce le serie siltrate per genere
    public Map<Long, GenericEntity> readSerieByGenere(String genere) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id"
                + " WHERE p.genere LIKE ?";
        genere = (genere != null && !genere.isEmpty()) ? "%" + genere + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, genere);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce le serie siltrate per rating
    public Map<Long, GenericEntity> readSerieByRating(String voto) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id"
                + " WHERE p.rating > ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, voto);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce le serie siltrate per anno pubblicazione
    public Map<Long, GenericEntity> readSerieByAnno(String anno) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id"
                + " WHERE p.anno_pubblicazione > ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, anno);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce le serie siltrate per attore
    public Map<Long, GenericEntity> readSerieByAttore(String attore) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id"
                + " WHERE p.cast LIKE ?";
        attore = (attore != null && !attore.isEmpty()) ? "%" + attore + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, attore);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    // ----------------------------da qui in poi per utente----------------------------

    // metodo che restituisce le serie di un utente filtrati per titolo
    public Map<Long, GenericEntity> readSerieByTitoloAndUtente(String titolo, Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.titolo LIKE ? AND u.id = ?";
        titolo = (titolo != null && !titolo.isEmpty()) ? "%" + titolo + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, titolo, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce le serie di un utente filtrati per genere
    public Map<Long, GenericEntity> readSerieByGenereAndUtente(String genere, Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.genere LIKE ? AND u.id = ?";
        genere = (genere != null && !genere.isEmpty()) ? "%" + genere + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, genere, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce le serie di un utente filtrati per rating
    public Map<Long, GenericEntity> readSerieByRatingAndUtente(String voto, Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.rating > ? AND u.id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, voto, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce le serie di un utente filtrati per anno pubblicazione
    public Map<Long, GenericEntity> readSerieByAnnoAndUtente(String anno, Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.anno_pubblicazione > ? AND u.id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, anno, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce le serie di un utente filtrati per attore
    public Map<Long, GenericEntity> readSerieByAttoreAndUtente(String attore, Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.cast LIKE ? AND u.id = ?";
        attore = (attore != null && !attore.isEmpty()) ? "%" + attore + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, attore, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }
}
