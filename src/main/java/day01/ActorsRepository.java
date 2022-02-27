package day01;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorsRepository {
    private DataSource dataSource;

    public ActorsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveActor(String name) {
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("insert into actors (actor_name) values (?)")) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot update: " + name, e);
        }
    }

    public List<String> findActorsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select actor_name from actors where actor_name like ?%")) {
            statement.setString(1, prefix);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String actorName = resultSet.getString("actor_name");
                    result.add(actorName);
                }
            }
        } catch (SQLException sqlException) {
            throw new IllegalStateException("Cannot make query!", sqlException);
        }
        return result;
    }
}