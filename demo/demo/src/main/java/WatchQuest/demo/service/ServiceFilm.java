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

    // metodo che restituisce 8 film
    public List<Film> find8() {
        return ritornaLista(getDao().read8());
    }

    // metodo che restituisce i film per id dell'utente
    public List<Film> findFilmByUtente(Long idUtente) {
        return ritornaLista(getDao().readFilmByUtente(idUtente));
    }

    // metodo che restituisce i film per titolo
    public List<Film> findFilmByTitolo(String titolo) {
        return ritornaLista(getDao().readFilmByTitolo(titolo));
    }

    // metodo che restituisce i film per genere
    public List<Film> findFilmByGenere(String genere) {
        return ritornaLista(getDao().readFilmByGenere(genere));
    }

    // metodo che restituisce i film per rating
    public List<Film> findFilmByRating(String voto) {
        return ritornaLista(getDao().readFilmByRating(voto));
    }

    // metodo che restituisce i film per anno pubblicazione
    public List<Film> findFilmByAnno(String anno) {
        return ritornaLista(getDao().readFilmByAnno(anno));
    }

    // metodo che restituisce i film per attore
    public List<Film> findFilmByAttore(String attore) {
        return ritornaLista(getDao().readFilmByAttore(attore));
    }

    // --------------------da qui in poi anche per utente--------------------
    // metodo che restituisce i film per titolo
    public List<Film> findFilmByTitoloAndUtente(String titolo, Long idUtente) {
        return ritornaLista(getDao().readFilmByTitoloAndUtente(titolo, idUtente));
    }

    // metodo che restituisce i film per genere
    public List<Film> findFilmByGenereAndUtente(String genere, Long idUtente) {
        return ritornaLista(getDao().readFilmByGenereAndUtente(genere, idUtente));
    }

    // metodo che restituisce i film per rating
    public List<Film> findFilmByRatingAndUtente(String voto, Long idUtente) {
        return ritornaLista(getDao().readFilmByRatingAndUtente(voto, idUtente));
    }

    // metodo che restituisce i film per anno pubblicazione
    public List<Film> findFilmByAnnoAndUtente(String anno, Long idUtente) {
        return ritornaLista(getDao().readFilmByAnnoAndUtente(anno, idUtente));
    }

    // metodo che restituisce i film per attore
    public List<Film> findFilmByAttoreAndUtente(String attore, Long idUtente) {
        return ritornaLista(getDao().readFilmByAttoreAndUtente(attore, idUtente));
    }

}
