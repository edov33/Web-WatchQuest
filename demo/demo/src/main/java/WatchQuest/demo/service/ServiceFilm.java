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

    // sopra ok
    // sotto tentativi per visualizzare film in base all'utente
    
    public List<Film> findFilmByUtente(Long idUtente){
        Map<Long, GenericEntity> result = getDao().readFilmByUtente(idUtente);
        List<Film> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Film) e);
        }
        return lista;
    }

}
