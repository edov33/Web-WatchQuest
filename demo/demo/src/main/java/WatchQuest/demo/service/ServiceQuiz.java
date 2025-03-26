package WatchQuest.demo.service;


import java.util.Map;

import org.springframework.stereotype.Service;

import WatchQuest.demo.dao.DaoQuiz;
import WatchQuest.demo.entity.Quiz;

@Service
public class ServiceQuiz extends GenericService<Long, Quiz, DaoQuiz> {

    @Override
    public Quiz construct(Map<String, String> entita) {
        return getContext().getBean(Quiz.class, entita);
    }

    public Long save(Map<String, String> mappa) {
        return getDao().create(construct(mappa));
    }
    
}
