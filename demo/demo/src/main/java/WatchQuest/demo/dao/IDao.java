package WatchQuest.demo.dao;

import java.util.Map;

import WatchQuest.demo.entity.GenericEntity;

public interface IDao<TipoID,E extends GenericEntity> {

    Long create(E e);
    Map<TipoID, GenericEntity> read();
    void update(E e);
    void delete(TipoID id);
    E readById(TipoID id);
    
}
