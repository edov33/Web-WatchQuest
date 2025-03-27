package WatchQuest.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import WatchQuest.demo.dao.DaoSerie;
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


    // metodo che restituisce le serie per titolo
    public List<Serie> findSerieByTitolo(String titolo) {
        Map<Long, GenericEntity> result = getDao().readSerieByTitolo(titolo);
        List<Serie> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Serie) e);
        }
        return lista;
    }

    // metodo che restituisce le serie per genere
    public List<Serie> findSerieByGenere(String genere) {
        Map<Long, GenericEntity> result = getDao().readSerieByGenere(genere);
        List<Serie> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Serie) e);
        }
        return lista;
    }

    // metodo che restituisce le serie per rating
    public List<Serie> findSerieByRating(String voto) {
        Map<Long, GenericEntity> result = getDao().readSerieByRating(voto);
        List<Serie> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Serie) e);
        }
        return lista;
    }

    // metodo che restituisce le serie per anno pubblicazione
    public List<Serie> findSerieByAnno(String anno) {
        Map<Long, GenericEntity> result = getDao().readSerieByAnno(anno);
        List<Serie> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Serie) e);
        }
        return lista;
    }

    // metodo che restituisce le serie per attore
    public List<Serie> findSerieByAttore(String attore) {
        Map<Long, GenericEntity> result = getDao().readSerieByAttore(attore);
        List<Serie> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Serie) e);
        }
        return lista;
    }

    // --------------------da qui per utente--------------------

    
    //metodo che restituisce le serie non ancora viste dall'utente

//"SELECT p.*, f.durata FROM Film f JOIN Programma p ON f.id_programma = p.id WHERE p.id NOT IN (SELECT up.id_programma FROM Utente_programma up WHERE up.id_utente = 1)"

    // metodo che restituisce le serie per id dell'utente
    public List<Serie> findSerieByUtente(Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readSerieByUtente(idUtente);
        List<Serie> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Serie) e);
        }
        return lista;
    }

    // metodo che restituisce le serie per titolo
    public List<Serie> findSerieByTitoloAndUtente(String titolo, Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readSerieByTitoloAndUtente(titolo, idUtente);
        List<Serie> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Serie) e);
        }
        return lista;
    }

    // metodo che restituisce le serie per genere
    public List<Serie> findSerieByGenereAndUtente(String genere, Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readSerieByGenereAndUtente(genere, idUtente);
        List<Serie> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Serie) e);
        }
        return lista;
    }

    // metodo che restituisce le serie per rating
    public List<Serie> findSerieByRatingAndUtente(String voto, Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readSerieByRatingAndUtente(voto, idUtente);
        List<Serie> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Serie) e);
        }
        return lista;
    }

    // metodo che restituisce le serie per anno pubblicazione
    public List<Serie> findSerieByAnnoAndUtente(String anno, Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readSerieByAnnoAndUtente(anno, idUtente);
        List<Serie> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Serie) e);
        }
        return lista;
    }

    // metodo che restituisce le serie per attore
    public List<Serie> findSerieByAttoreAndUtente(String attore, Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readSerieByAttoreAndUtente(attore, idUtente);
        List<Serie> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Serie) e);
        }
        return lista;
    }
}
