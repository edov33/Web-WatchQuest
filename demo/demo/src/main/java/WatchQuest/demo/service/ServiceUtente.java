package WatchQuest.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import WatchQuest.demo.dao.DaoUtente;
import WatchQuest.demo.entity.GenericEntity;
import WatchQuest.demo.entity.Programma;
import WatchQuest.demo.entity.Utente;

@Service
public class ServiceUtente extends GenericService<Long, Utente, DaoUtente> {

    @Override
    public Utente construct(Map<String, String> entita) {
        return getContext().getBean(Utente.class, entita);
    }

    public Long save(Map<String, String> mappa) {
        return getDao().create(construct(mappa));
    }

    // sopra ok
    // sotto tentativi per visualizzare film in base all'utente
    public List<Programma> findFilmByIdUtente(Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readProgrammaByIdUtente(idUtente);
        List<Programma> ris = new ArrayList<>();
        for (GenericEntity g : result.values()) {
            ris.add((Programma) g);
        }
        return ris;
    }

    // @Override
    // public List<Utente> findAll() {
    //     List<Utente> ris = new ArrayList<>();
    //     for (Utente u : super.findAll()) {
    //         u.setProgrammi(findFilmByIdUtente(u.getId()));
    //     }
    //     return ris;
    // }

}
