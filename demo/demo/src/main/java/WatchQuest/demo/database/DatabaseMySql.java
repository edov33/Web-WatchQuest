package WatchQuest.demo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

/*tramite l'annotazione @Service indico a Spring che questa classe
 * è una classe di servizio e quindi deve essere gestita da Spring
 * cioè andrà direttmente spring a creare un bean(un oggetto) di questa classe
 * senza che io debba farlo manualmente in un context(il contenitore dei Bean)
 * spring creerà un'istanza di questa classe e la metterà a disposizione
 * per essere iniettata in altre classi
 * Un bean di servizio è una classe che implementa la logica di business
 * cioè la logica usata nella classi che hanno funzionalità legate al business dell'applicazione
 * Sring si occuperà sia della creazione del bean e della sua gestione 
 * Di default il bean sarà singleton cioè sarà unico per tutta l'applicazione
Questi sono i concetti chiavi di @Service:
1. Gestione automatica del bean: Spring creerà un'istanza di questa classe e la metterà a disposizione per essere iniettata in altre 
classi senza bisogno di istanziarla manualmente.

2. Pattern Singleton (di default): il bean creato sarà un singleton, cioè ci sarà una sola istanza della classe in tutta l'applicazione, 
evitando la creazione ripetuta e non necessaria di oggetti.

3. Integrazione con Dependency Injection: qualsiasi altra classe che ha bisogno di un oggetto DatabaseMySql può 
semplicemente dichiararlo come dipendenza, e Spring lo fornirà automaticamente.

4. Separazione dei ruoli: i componenti di accesso ai dati dovrebbero essere definiti come bean di servizio per seguire il 
principio della Single Responsibility
(Il SRP afferma che: "Una classe dovrebbe avere una e una sola ragione per cambiare."
In altre parole, ogni classe dovrebbe avere un solo scopo e una sola responsabilità ben definita all'interno dell'applicazione.).
*/
@Service
public class DatabaseMySql implements IDatabase {

    /*
     * prendo i valori dal file di configurazione in questo modo
     * posso cambiarli senza dover ricompilare il codice
     * basta modificare i valori salvati nel file di configurazione
     * con l'annotazione @Value("${spring.datasource.username}") prendo
     * il valore della variabile username dal file di configurazione
     * e lo inietto nella proprietà username
     * posso usare questa annotazione per tutte le proprietà che voglio
     * e anche per i metodi
     * Funziona solo in bean gestiti da Spring (annotati
     * con @Component, @Service, @Repository, ecc.)
     * https://docs.spring.io/spring-boot/appendix/application-properties/index.html
     * #appendix.application-properties.data
     */
    @Value("${spring.datasource.username}")
    private String username;

    // L'annotazione @Value in Spring viene utilizzata per iniettare valori nelle
    // variabili di istanza di una classe.
    // Questi valori possono provenire da:
    // ✔ File di configurazione (application.properties o application.yml)
    /*
     * l'annotazione @Value può anche essere utilizzata a livello di metodo per
     * iniettare valori
     * nei parametri del metodo.
     */
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;

    private Connection connection;

    // driver
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    // spring creerà un oggetto- Bean - di tipo DatabaseMySql
    // tramite costruttore vuoto
    // poi inieterrà i valori all'interno delle proprietà prendendoli dal file di
    // configurazione
    public DatabaseMySql() {
    }

    // dopo la creazione in Singleton di DatabaseMySql vorrei che venisse invocato
    // il metodo openConnection in modo da aprire la connessione
    /*
     * L'annotazione @PostConstruct viene usata per indicare un metodo che deve
     * essere eseguito automaticamente
     * dopo che il bean(l'istanza di tipo DatabaseMySql)
     * è stato creato e tutte le dipendenze sono state iniettate da Spring.
     * Come funziona @PostConstruct?
     * 1. Spring crea un'istanza della classe DatabaseMySql (perché è annotata
     * con @Service).
     * 2. Spring inietta i valori provenienti dal file application.properties nelle
     * variabili url, username, password, ecc.
     * 3. Dopo che tutte le dipendenze sono state risolte, Spring chiama
     * automaticamente il metodo openConnection().
     * 4. La connessione al database viene aperta.
     * 
     * Quando si usa @PostConstruct?
     * Per eseguire operazioni di inizializzazione dopo la creazione del bean.
     * Per aprire connessioni a database, caricare dati iniziali o configurare
     * risorse.
     * Per evitare di dover chiamare il metodo manualmente in altre parti del
     * codice.
     * In questo caso di aprire la connessione senza dover chiamare il metodo
     * esplicitamente
     * inoltre stiamo garantendo che un'operazione venga eseguita appena il bean
     * viene creato e inizializzato.
     */
    @PostConstruct
    private void openConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long executeDML(String query, String... parametri) {
        Long id = -1L;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String generatedColumns[] = { "id" };
            ps = connection.prepareStatement(query, generatedColumns);
            for (int i = 0; i < parametri.length; i++) {
                ps.setString(i + 1, parametri[i]);
            }
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (Exception e) {
            System.out.println("Errore esecuzione query: " + e.getMessage());
            e.printStackTrace();
            return -2L;
        } finally {
            close(ps, rs);
            closeConnection();
        }
        return id;
    }

    @Override
    public Map<Long, Map<String, String>> executeDQL(String query, String... parametri) {
        Map<Long, Map<String, String>> result = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(query);
            for (int i = 0; i < parametri.length; i++) {
                ps.setString(i + 1, parametri[i]);
            }
            rs = ps.executeQuery();
            Map<String, String> mappaProprietà;
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                mappaProprietà = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    mappaProprietà.put(rs.getMetaData().getColumnName(i),
                            rs.getString(i));
                }
                result.put(rs.getLong("id"), mappaProprietà);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(ps, rs);
            closeConnection();
        }
        return result;
    }

}
