package WatchQuest.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import WatchQuest.demo.dao.IDao;
import WatchQuest.demo.entity.GenericEntity;
import lombok.Getter;

@Getter
public abstract class GenericService<TipoID, E extends GenericEntity, D extends IDao<TipoID, E>> {

    // per poter funzionare un service deve poter chiedere le info al dao
    @Autowired
    private D dao;
    // @Autowired
    // public GenericService(D dao){
    // this.dao = dao;
    // }
    @Autowired
    private ApplicationContext context;

    // metodo che richiama il read dei vari dao
    public List<E> findAll() {
        Map<TipoID, GenericEntity> result = dao.read();
        List<E> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((E) e);
        }
        return lista;
    }

    // metodo che cerca un'entita per id
    public E findById(TipoID id) {
        return dao.readById(id);
    }

    // metodo che crea una entita
    public abstract E construct(Map<String, String> entita);

    // metodo che aggiorna una entita
    public void update(Map<String, String> entita) {
        E entity = construct(entita);
        dao.update(entity);
    }

    // metodo che cancella una entita
    public void delete(TipoID id) {
        dao.delete(id);
    }

}
