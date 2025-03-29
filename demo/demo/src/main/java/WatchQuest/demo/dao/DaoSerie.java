package WatchQuest.demo.dao;

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
        return ritornaMappa(databaseMySql.executeDQL(query));
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

    // metodo che semplifica tutti gli altri metodi
    private Map<Long, GenericEntity> ritornaMappa(Map<Long, Map<String, String>> result) {
        Map<Long, GenericEntity> ris = new LinkedHashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce 8 serie
    public Map<Long, GenericEntity> read8() {
        String query = "SELECT p.*, s.stagioni, s.episodi FROM serie s JOIN programma p ON s.id_programma = p.id ORDER BY rand() LIMIT 8";
        return ritornaMappa(databaseMySql.executeDQL(query));
    }

    // metodo che restituisce le serie viste da un utente
    public Map<Long, GenericEntity> readSerieByUtente(Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id "
                + "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id "
                + "WHERE u.id = ? ORDER BY p.titolo ASC";
        return ritornaMappa(databaseMySql.executeDQL(query, String.valueOf(idUtente)));
    }

    // metodo che restituisce le serie filtrate per titolo
    public Map<Long, GenericEntity> readSerieByTitolo(String titolo) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id"
                + " WHERE p.titolo LIKE ? ORDER BY p.titolo ASC";
        titolo = (titolo != null && !titolo.isEmpty()) ? "%" + titolo + "%" : "%";
        return ritornaMappa(databaseMySql.executeDQL(query, titolo));
    }

    // metodo che restituisce le serie filtrate per genere
    public Map<Long, GenericEntity> readSerieByGenere(String genere) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id"
                + " WHERE p.genere LIKE ? ORDER BY p.titolo ASC";
        genere = (genere != null && !genere.isEmpty()) ? "%" + genere + "%" : "%";
        return ritornaMappa(databaseMySql.executeDQL(query, genere));
    }

    // metodo che restituisce le serie filtrate per rating
    public Map<Long, GenericEntity> readSerieByRating(String voto) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id"
                + " WHERE p.rating > ? ORDER BY p.titolo ASC";
        return ritornaMappa(databaseMySql.executeDQL(query, voto));
    }

    // metodo che restituisce le serie filtrate per anno pubblicazione
    public Map<Long, GenericEntity> readSerieByAnno(String anno) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id"
                + " WHERE p.anno_pubblicazione > ? ORDER BY p.titolo ASC";
        return ritornaMappa(databaseMySql.executeDQL(query, anno));
    }

    // metodo che restituisce le serie filtrate per attore
    public Map<Long, GenericEntity> readSerieByAttore(String attore) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id"
                + " WHERE p.cast LIKE ? ORDER BY p.titolo ASC";
        attore = (attore != null && !attore.isEmpty()) ? "%" + attore + "%" : "%";
        return ritornaMappa(databaseMySql.executeDQL(query, attore));
    }

    // ----------------------------da qui in poi per
    // utente----------------------------

    // metodo che restituisce le serie di un utente filtrati per titolo
    public Map<Long, GenericEntity> readSerieByTitoloAndUtente(String titolo, Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.titolo LIKE ? AND u.id = ? ORDER BY p.titolo ASC";
        titolo = (titolo != null && !titolo.isEmpty()) ? "%" + titolo + "%" : "%";
        return ritornaMappa(databaseMySql.executeDQL(query, titolo, String.valueOf(idUtente)));
    }

    // metodo che restituisce le serie di un utente filtrati per genere
    public Map<Long, GenericEntity> readSerieByGenereAndUtente(String genere, Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.genere LIKE ? AND u.id = ? ORDER BY p.titolo ASC";
        genere = (genere != null && !genere.isEmpty()) ? "%" + genere + "%" : "%";
        return ritornaMappa(databaseMySql.executeDQL(query, genere, String.valueOf(idUtente)));
    }

    // metodo che restituisce le serie di un utente filtrati per rating
    public Map<Long, GenericEntity> readSerieByRatingAndUtente(String voto, Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.rating > ? AND u.id = ? ORDER BY p.titolo ASC";
        return ritornaMappa(databaseMySql.executeDQL(query, voto, String.valueOf(idUtente)));
    }

    // metodo che restituisce le serie di un utente filtrati per anno pubblicazione
    public Map<Long, GenericEntity> readSerieByAnnoAndUtente(String anno, Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.anno_pubblicazione > ? AND u.id = ? ORDER BY p.titolo ASC";
        return ritornaMappa(databaseMySql.executeDQL(query, anno, String.valueOf(idUtente)));
    }

    // metodo che restituisce le serie di un utente filtrati per attore
    public Map<Long, GenericEntity> readSerieByAttoreAndUtente(String attore, Long idUtente) {
        String query = "SELECT p.*, s.episodi, s.stagioni FROM serie s JOIN programma p ON s.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.cast LIKE ? AND u.id = ? ORDER BY p.titolo ASC";
        attore = (attore != null && !attore.isEmpty()) ? "%" + attore + "%" : "%";
        return ritornaMappa(databaseMySql.executeDQL(query, attore, String.valueOf(idUtente)));
    }
}
