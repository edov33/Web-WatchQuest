package WatchQuest.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import WatchQuest.demo.dao.DaoFilm;
import WatchQuest.demo.entity.Film;
import WatchQuest.demo.entity.GenericEntity;

@Service
public class ServiceFilm extends GenericService<Long, Film, DaoFilm> {

    @Override
    public Film construct(Map<String, String> entita) {
        return getContext().getBean(Film.class, entita);
    }

    public Long save(Map<String, String> mappa) {
        return getDao().create(construct(mappa));
    }
    
    // metodo che restituisce i film per id dell'utente
    public List<Film> findFilmByUtente(Long idUtente){
        Map<Long, GenericEntity> result = getDao().readFilmByUtente(idUtente);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

    // metodo che restituisce i film per titolo
    public List<Film> findFilmByTitolo(String titolo) {
        Map<Long, GenericEntity> result = getDao().readFilmByTitolo(titolo);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

    // metodo che restituisce i film per genere
    public List<Film> findFilmByGenere(String genere) {
        Map<Long, GenericEntity> result = getDao().readFilmByGenere(genere);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

    // metodo che restituisce i film per rating
    public List<Film> findFilmByRating(String voto) {
        Map<Long, GenericEntity> result = getDao().readFilmByRating(voto);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

    // metodo che restituisce i film per anno pubblicazione
    public List<Film> findFilmByAnno(String anno) {
        Map<Long, GenericEntity> result = getDao().readFilmByAnno(anno);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

    // metodo che restituisce i film per attore
    public List<Film> findFilmByAttore(String attore) {
        Map<Long, GenericEntity> result = getDao().readFilmByAttore(attore);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

    // --------------------da qui in poi anche per utente--------------------
    // metodo che restituisce i film per titolo
    public List<Film> findFilmByTitoloAndUtente(String titolo, Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readFilmByTitoloAndUtente(titolo, idUtente);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

    // metodo che restituisce i film per genere
    public List<Film> findFilmByGenereAndUtente(String genere, Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readFilmByGenereAndUtente(genere, idUtente);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

    // metodo che restituisce i film per rating
    public List<Film> findFilmByRatingAndUtente(String voto, Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readFilmByRatingAndUtente(voto, idUtente);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

    // metodo che restituisce i film per anno pubblicazione
    public List<Film> findFilmByAnnoAndUtente(String anno, Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readFilmByAnnoAndUtente(anno, idUtente);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

    // metodo che restituisce i film per attore
    public List<Film> findFilmByAttoreAndUtente(String attore, Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readFilmByAttoreAndUtente(attore, idUtente);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

}
