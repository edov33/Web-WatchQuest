package WatchQuest.demo.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.entity.GenericEntity;
import WatchQuest.demo.entity.Serie;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Service
@Data
@EqualsAndHashCode(callSuper = true)
public class DaoSerie extends DaoProgramma implements IDao<Long, Serie> {

    private final DatabaseMySql databaseMySql;
    private final ApplicationContext context;

    @Override
    public Long create(Serie s) {
        Long id = super.createProgramma(s);
        String query = "INSERT INTO serie (stagioni, episodi, id_programma) values(?,?,?)";
        if (id != null) {
            return databaseMySql.executeDML(query, String.valueOf(s.getStagioni()), String.valueOf(s.getEpisodi()),
                    String.valueOf(id));
        }
        return null;
    }

    @Override
    public Map<Long, GenericEntity> read() {
        String query = "SELECT p.*, s.stagioni, s.episodi FROM serie s JOIN programma p ON s.id_programma = p.id";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            ris.put(coppia.getKey(), context.getBean(Serie.class, coppia.getValue()));
        }
        return ris;
    }

    @Override
    public void update(Serie s) {
        super.updateProgramma(s);
        String query = "UPDATE serie SET stagioni=?, episodi=? WHERE id_programma=?";
        databaseMySql.executeDML(query, String.valueOf(s.getStagioni()), String.valueOf(s.getEpisodi()),
                String.valueOf(s.getId()));
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public Serie readById(Long id) {
        String query = "SELECT * FROM serie WHERE id = ?";
        Map<Long, Map<String, String>> result = databaseMySql.executeDQL(query, String.valueOf(id));
        if (result.size() == 1) {
            for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
                return context.getBean(Serie.class, coppia);
            }
        }
        return null;
    }

}
