package WatchQuest.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import WatchQuest.demo.dao.DaoSerie;
import WatchQuest.demo.entity.Film;
import WatchQuest.demo.entity.GenericEntity;
import WatchQuest.demo.entity.Serie;

@Service
public class ServiceSerie extends GenericService<Long, Serie, DaoSerie> {

    @Override
    public Serie construct(Map<String, String> entita) {
        return getContext().getBean(Serie.class, entita);
    }

    public Long save(Map<String, String> mappa) {
        return getDao().create(construct(mappa));
    }

    // metodo che restituisce 8 serie
    public List<Serie> find8() {
        return ritornaLista(getDao().read8());
    }

    // metodo che restituisce le serie per titolo
    public List<Serie> findSerieByTitolo(String titolo) {
        return ritornaLista(getDao().readSerieByTitolo(titolo));
    }

    // metodo che restituisce le serie per genere
    public List<Serie> findSerieByGenere(String genere) {
        return ritornaLista(getDao().readSerieByGenere(genere));
    }

    // metodo che restituisce le serie per rating
    public List<Serie> findSerieByRating(String voto) {
        return ritornaLista(getDao().readSerieByRating(voto));
    }

    // metodo che restituisce le serie per anno pubblicazione
    public List<Serie> findSerieByAnno(String anno) {
        return ritornaLista(getDao().readSerieByAnno(anno));
    }

    // metodo che restituisce le serie per attore
    public List<Serie> findSerieByAttore(String attore) {
        return ritornaLista(getDao().readSerieByAttore(attore));
    }

    // --------------------da qui per utente--------------------

    // metodo che restituisce le serie non ancora viste dall'utente

    // "SELECT p.*, f.durata FROM Film f JOIN Programma p ON f.id_programma = p.id
    // WHERE p.id NOT IN (SELECT up.id_programma FROM Utente_programma up WHERE
    // up.id_utente = 1)"

    // metodo che restituisce le serie per id dell'utente
    public List<Serie> findSerieByUtente(Long idUtente) {
        return ritornaLista(getDao().readSerieByUtente(idUtente));
    }

    // metodo che restituisce le serie per titolo
    public List<Serie> findSerieByTitoloAndUtente(String titolo, Long idUtente) {
        return ritornaLista(getDao().readSerieByTitoloAndUtente(titolo, idUtente));
    }

    // metodo che restituisce le serie per genere
    public List<Serie> findSerieByGenereAndUtente(String genere, Long idUtente) {
        return ritornaLista(getDao().readSerieByGenereAndUtente(genere, idUtente));
    }

    // metodo che restituisce le serie per rating
    public List<Serie> findSerieByRatingAndUtente(String voto, Long idUtente) {
        return ritornaLista(getDao().readSerieByRatingAndUtente(voto, idUtente));
    }

    // metodo che restituisce le serie per anno pubblicazione
    public List<Serie> findSerieByAnnoAndUtente(String anno, Long idUtente) {
        return ritornaLista(getDao().readSerieByAnnoAndUtente(anno, idUtente));
    }

    // metodo che restituisce le serie per attore
    public List<Serie> findSerieByAttoreAndUtente(String attore, Long idUtente) {
        return ritornaLista(getDao().readSerieByAttoreAndUtente(attore, idUtente));
    }
}
