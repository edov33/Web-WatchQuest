package WatchQuest.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import WatchQuest.demo.dao.DaoQuiz;
import WatchQuest.demo.entity.GenericEntity;
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

    // metodo che restituisce i quiz in base al genere
    public List<Quiz> findQuizByGenere(String genere) {
        Map<Long, GenericEntity> result = getDao().readQuizByGenere(genere);
        List<Quiz> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Quiz) e);
        }
        return lista;
    }

    // metodo che restituisce un numero di domande casuali scelto
    public List<Quiz> findQuizRandNum() {
        Map<Long, GenericEntity> result = getDao().readQuizRandNum();
        List<Quiz> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Quiz) e);
        }
        return lista;
    }

    // metodo che restituisce un numero di domande casuali dello stesso genere
    public List<Quiz> findQuizRandByGenere(String genere) {
        Map<Long, GenericEntity> result = getDao().readQuizRandByGenere(genere);
        List<Quiz> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Quiz) e);
        }
        return lista;
    }

    // metodo che restituisce tutte le domande non fatte da un utente
    public List<Quiz> findQuizNuoviByUtente(Long idUtente) {
        Map<Long, GenericEntity> result = getDao().readQuizNuoviByUtente(idUtente);
        List<Quiz> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((Quiz) e);
        }
        return lista;
    }

}
