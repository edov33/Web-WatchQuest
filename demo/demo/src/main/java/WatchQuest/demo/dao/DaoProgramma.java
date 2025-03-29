package WatchQuest.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.entity.Programma;
import lombok.Data;

@Data
@Service
public abstract class DaoProgramma {
    @Autowired
    private DatabaseMySql databaseMySql;

    public Long createProgramma(Programma p) {
        String query = "INSERT INTO Programma (titolo,descrizione,genere,anno_pubblicazione,classificazione,rating,cast,regista,lingua_originale,url,trailer,wiki) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        return databaseMySql.executeDML(query, p.getTitolo(), p.getDescrizione(), p.getGenere(),
                String.valueOf(p.getAnno_pubblicazione()), p.getClassificazione(), String.valueOf(p.getRating()),
                p.getCast(), p.getRegista(), p.getLingua_originale(), p.getUrl());
    }

    public void updateProgramma(Programma p) {
        String query = "UPDATE Programma SET titolo=?, descrizione=?, genere=?, anno_pubblicazione=?, classificazione=?, rating=?, cast=?, regista=?, lingua_originale=?, url=?, trailer=?, wiki=? WHERE id = ?";
        databaseMySql.executeDML(query, p.getTitolo(), p.getDescrizione(), p.getGenere(),
                String.valueOf(p.getAnno_pubblicazione()), p.getClassificazione(), String.valueOf(p.getRating()),
                p.getCast(), p.getRegista(), p.getLingua_originale(), p.getUrl(), String.valueOf(p.getId()));
    }

    public void delete(Long id) {
        String query = "DELETE FROM Programma WHERE id = ?";
        databaseMySql.executeDML(query, String.valueOf(id));
    }
}
