package WatchQuest.demo.dao;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.entity.GenericEntity;
import lombok.Data;

@Data
@Service
public abstract class DaoUtente implements IDao{
    
    private final DatabaseMySql databaseMySql;
    private final ApplicationContext context;
    //private final DaoSerie daoSerie;
    private final DaoFilm daoFilm;
    


}
