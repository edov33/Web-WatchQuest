package WatchQuest.demo.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.entity.GenericEntity;
import WatchQuest.demo.entity.SerieTv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Service
@Data
@EqualsAndHashCode(callSuper = true)
public class DaoSerie extends DaoProgramma implements IDao<Long, SerieTv> {

    private final DatabaseMySql databaseMySql;
    private final ApplicationContext context;

    @Override
    public Long create(SerieTv e) {
        Long id = super.createProgramma(e);
        String query = "INSERT INTO serie (stagioni, episodi, id_programma) values(?,?,?)";
        if (id != null) {
            return databaseMySql.executeDML(query, String.valueOf(e.getStagioni()), String.valueOf(e.getEpisodi()),
                    String.valueOf(id));
        }
        return null;
    }

    @Override
    public Map<Long, GenericEntity> read() {
        String query = "SELECT * FROM serie JOIN programma ON serie.id_programma = programma.id";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            SerieTv s = context.getBean(SerieTv.class, coppia.getValue());
            ris.put(coppia.getKey(), s);
        }
        return ris;
    }

    @Override
    public void update(SerieTv e) {
        super.updateProgramma(e);
        String query = "UPDATE serie SET stagioni=?, episodi=? WHERE id=?";
        databaseMySql.executeDML(query, String.valueOf(e.getStagioni()), String.valueOf(e.getEpisodi()),
                String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) {
        // *String query = "DELETE FROM serie WHERE id = ?";
        // databaseMySql.executeDML(query, String.valueOf(id));
        super.delete(id);
    }

    @Override
    public SerieTv readById(Long id) {

    }

}
