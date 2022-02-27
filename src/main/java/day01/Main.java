package day01;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/movies-actors?useUnicode=true");
            dataSource.setUserName("root");
            dataSource.setPassword("root");
        } catch (SQLException e) {
            throw new IllegalStateException("Database cannot be reached!");
        }

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        ActorsRepository actorsRepository = new ActorsRepository(dataSource);
        actorsRepository.saveActor("John Doe");
        System.out.println(actorsRepository.findActorsWithPrefix("j"));
    }
}