package WatchQuest.demo.service;


import java.util.Map;

import org.springframework.stereotype.Service;

import WatchQuest.demo.dao.DaoSerie;
import WatchQuest.demo.entity.Serie;

@Service
public class ServiceSerie extends GenericService<Long, Serie, DaoSerie> {

    @Override
    public Serie construct(Map<String, String> entita) {
        return getContext().getBean(Serie.class, entita);
    }

    public Long save(Map<String, String> mappa) {
        return getDao().create(construct(mappa));
    }

}
