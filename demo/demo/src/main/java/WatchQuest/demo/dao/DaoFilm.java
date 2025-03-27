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

    // metodo che restituisce i film visti da un utente
    public Map<Long, GenericEntity> readFilmByUtente(Long idUtente) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id " +
                "WHERE u.id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce i film filtrati per titolo
    public Map<Long, GenericEntity> readFilmByTitolo(String titolo) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id"
                + " WHERE p.titolo LIKE ?";
        titolo = (titolo != null && !titolo.isEmpty()) ? "%" + titolo + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, titolo);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce i film filtrati per genere
    public Map<Long, GenericEntity> readFilmByGenere(String genere) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id"
                + " WHERE p.genere LIKE ?";
        genere = (genere != null && !genere.isEmpty()) ? "%" + genere + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, genere);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce i film filtrati per rating
    public Map<Long, GenericEntity> readFilmByRating(String voto) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id"
                + " WHERE p.rating > ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, voto);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce i film filtrati per anno pubblicazione
    public Map<Long, GenericEntity> readFilmByAnno(String anno) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id"
                + " WHERE p.anno_pubblicazione > ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, anno);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce i film filtrati per attore
    public Map<Long, GenericEntity> readFilmByAttore(String attore) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id"
                + " WHERE p.cast LIKE ?";
        attore = (attore != null && !attore.isEmpty()) ? "%" + attore + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, attore);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    //  ----------------------------da qui in poi per utente----------------------------

    // metodo che restituisce i film di un utente filtrati per titolo
    public Map<Long, GenericEntity> readFilmByTitoloAndUtente(String titolo, Long idUtente) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.titolo LIKE ? AND u.id = ?";
        titolo = (titolo != null && !titolo.isEmpty()) ? "%" + titolo + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, titolo, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce i film di un utente filtrati per genere
    public Map<Long, GenericEntity> readFilmByGenereAndUtente(String genere, Long idUtente) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.genere LIKE ? AND u.id = ?";
        genere = (genere != null && !genere.isEmpty()) ? "%" + genere + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, genere, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce i film di un utente filtrati per rating
    public Map<Long, GenericEntity> readFilmByRatingAndUtente(String voto, Long idUtente) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.rating > ? AND u.id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, voto, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce i film di un utente filtrati per anno pubblicazione
    public Map<Long, GenericEntity> readFilmByAnnoAndUtente(String anno, Long idUtente) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.anno_pubblicazione > ? AND u.id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, anno, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

    // metodo che restituisce i film di un utente filtrati per attore
    public Map<Long, GenericEntity> readFilmByAttoreAndUtente(String attore, Long idUtente) {
        String query = "SELECT p.*, f.durata FROM film f JOIN programma p ON f.id_programma = p.id " +
                "JOIN utente_programma up ON p.id = up.id_programma JOIN utente u ON up.id_utente = u.id" +
                " WHERE p.cast LIKE ? AND u.id = ?";
        attore = (attore != null && !attore.isEmpty()) ? "%" + attore + "%" : "%";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, attore, String.valueOf(idUtente));
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Film.class, coppia.getValue()));
        }
        return ris;
    }

}