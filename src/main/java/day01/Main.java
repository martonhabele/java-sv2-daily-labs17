package day01;

import org.mariadb.jdbc.MariaDbDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/movies-actors");
            dataSource.setUserName("root");
            dataSource.setPassword("root");
        } catch (SQLException e) {
            throw new IllegalStateException("Database cannot be reached!");
        }

        ActorsRepository actorsRepository = new ActorsRepository(dataSource);
        actorsRepository.saveActor("Jack Doe");
    }
}