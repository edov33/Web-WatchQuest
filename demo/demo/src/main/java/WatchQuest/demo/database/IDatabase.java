package WatchQuest.demo.database;

import java.util.Map;

public interface IDatabase {
    
    Long executeDML(String query,String... parametri);
    Map<Long,Map<String,String>> executeDQL(String query, String... parametri);

}
