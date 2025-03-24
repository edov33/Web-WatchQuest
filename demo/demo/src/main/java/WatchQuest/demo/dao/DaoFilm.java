package WatchQuest.demo.dao;

import java.util.Map;

import WatchQuest.demo.entity.Film;
import WatchQuest.demo.entity.GenericEntity;

public class DaoFilm extends DaoProgramma implements IDao<Long, Film> {

    @Override
    public Long create(Film e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Map<Long, GenericEntity> read() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public void update(Film e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Film readById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readById'");
    }
    
}
