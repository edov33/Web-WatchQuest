package WatchQuest.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import WatchQuest.demo.dao.DaoUtente;
import WatchQuest.demo.entity.Film;
import WatchQuest.demo.entity.GenericEntity;
import WatchQuest.demo.entity.Programma;
import WatchQuest.demo.entity.Serie;
import WatchQuest.demo.entity.Utente;
import lombok.Data;

@Service
@Data
public class ServiceUtente extends GenericService<Long, Utente, DaoUtente> {

    private final ServiceSerie serviceSerie;
    private final ServiceFilm serviceFilm;

    @Override
    public Utente construct(Map<String, String> entita) {
        return getContext().getBean(Utente.class, entita);
    }

    public Long save(Map<String, String> mappa) {
        return getDao().create(construct(mappa));
    }

    // metodo che restituisce film e serie viste dall'utente
    public List<Programma> findProgrammaByUtente(Long idUtente) {
        List<Serie> serie = serviceSerie.findSerieByUtente(idUtente);
        List<Film> film = serviceFilm.findFilmByUtente(idUtente);
        List<Programma> programmi = new ArrayList<>();
        programmi.addAll(serie);
        programmi.addAll(film);
        return programmi;
    }

    public Utente findAllProgrammiUnUtente(Long idUtente) {
        Utente u = getDao().readById(idUtente);
        u.setFilm(findFilmByUtente(u.getId()));
        u.setSerie(findSerieByUtente(u.getId()));
        return u;
    }

    public List<Utente> findAllProgrammiUtente() {
        Map<Long, GenericEntity> result = getDao().read();
        List<Utente> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            if (e instanceof Utente u) {
                u.setFilm(findFilmByUtente(u.getId()));
                u.setSerie(findSerieByUtente(u.getId()));
                lista.add(u);
            }
        }
        return lista;
    }

    // metodo che restituisce film viste dall'utente
    public List<Film> findFilmByUtente(Long idUtente) {
        return serviceFilm.findFilmByUtente(idUtente);
    }

    // metodo che restituisce serie viste dall'utente
    public List<Serie> findSerieByUtente(Long idUtente) {
        return serviceSerie.findSerieByUtente(idUtente);
    }

    // metodo che filtra gli utenti per nominativo
    public List<Utente> findUtenteByNominativo(String nome, String cognome) {
        List<Utente> lista = new ArrayList<>();
        Map<Long, GenericEntity> mappa = getDao().readUtenteByNominativo(nome, cognome);
        for (Entry<Long, GenericEntity> coppia : mappa.entrySet()) {
            lista.add((Utente) coppia.getValue());
        }
        return lista;
    }

    // metodo che filtra gli utenti per nome
    public List<Utente> findUtenteByNome(String nome) {
        List<Utente> lista = new ArrayList<>();
        Map<Long, GenericEntity> mappa = getDao().readUtenteByNome(nome);
        for (Entry<Long, GenericEntity> coppia : mappa.entrySet()) {
            lista.add((Utente) coppia.getValue());
        }
        return lista;
    }

    // metodo che filtra gli utenti per cognome
    public List<Utente> findUtenteByCognome(String cognome) {
        List<Utente> lista = new ArrayList<>();
        Map<Long, GenericEntity> mappa = getDao().readUtenteByCognome(cognome);
        for (Entry<Long, GenericEntity> coppia : mappa.entrySet()) {
            lista.add((Utente) coppia.getValue());
        }
        return lista;
    }

    // metodo che filtra gli utenti per nome
    public List<Utente> findUtenteByUsername(String username) {
        List<Utente> lista = new ArrayList<>();
        Map<Long, GenericEntity> mappa = getDao().readUtenteByUsername(username);
        for (Entry<Long, GenericEntity> coppia : mappa.entrySet()) {
            lista.add((Utente) coppia.getValue());
        }
        return lista;
    }

    // metodo che filtra gli utenti per email
    public List<Utente> findUtenteByEmail(String email) {
        List<Utente> lista = new ArrayList<>();
        Map<Long, GenericEntity> mappa = getDao().readUtenteByEmail(email);
        for (Entry<Long, GenericEntity> coppia : mappa.entrySet()) {
            lista.add((Utente) coppia.getValue());
        }
        return lista;
    }

    // TODO - non va, problemi con super.findById
    // @Override
    // public Utente findById(Long id) {
    // Utente u = super.findById(id);
    // System.out.println(u);
    // u.setFilm(findFilmByUtente(Stringu.getId()));
    // u.setSerie(findSerieByUtente(u.getId()));
    // return u;
    // }
    // sotto tentativi per visualizzare film in base all'utente
    // public List<Programma> findFilmByIdUtente(Long idUtente) {
    // Map<Long, GenericEntity> result = getDao().readProgrammaByIdUtente(idUtente);
    // List<Programma> ris = new ArrayList<>();
    // for (GenericEntity g : result.values()) {
    // ris.add((Programma) g);
    // }
    // return ris;
    // }

    // @Override
    // public List<Utente> findAll() {
    // List<Utente> ris = new ArrayList<>();
    // for (Utente u : super.findAll()) {
    // u.setProgrammi(findFilmByIdUtente(u.getId()));
    // }
    // return ris;
    // }

}
