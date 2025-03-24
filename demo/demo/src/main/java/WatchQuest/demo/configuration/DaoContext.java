package WatchQuest.demo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import WatchQuest.demo.database.DatabaseMySql;
import WatchQuest.demo.database.IDatabase;

@Configuration
public class DaoContext {

    @Bean
    @Qualifier("databaseMYSQL")
    public IDatabase databaseMySQL(){
        return new DatabaseMySql();
    }
    
}
