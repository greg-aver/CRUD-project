package DAO;

import lombok.Cleanup;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO<User> {
//    UserDAO<User> userDAO = new UserJdbcDAO(connection);
    private Connection connection;
    private static UserJdbcDAO userJdbcDAO;

    private UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    public static UserJdbcDAO getUserJdbcDAO(Connection connection) {
        if (userJdbcDAO == null) {
            userJdbcDAO = new UserJdbcDAO(connection);
        }
        return userJdbcDAO;
    }

    public User getById(int id) {
        try {
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new User(
                    id,
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age"));

        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }


    public void add(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO users (name, surname, age) VALUES (?, ?, ?)"
        );) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void delete(int id) {
        try {
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
             preparedStatement.execute();

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void update(User user) {
        try {
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET name = ?, surname = ?, age = ? WHERE id = ? "
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users"
            );
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age")
                ));
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }

        return users;
    }
}
