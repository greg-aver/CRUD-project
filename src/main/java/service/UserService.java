package service;

import DAO.UserDAO;
import factory.RealizationHibernateDAO;
import factory.RealizationJdbcDAO;
import factory.UserDAOFactory;
import model.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class UserService {
    private UserDAO<User> userDAO;
    private UserDAOFactory userDAOFactory;
    private static UserService service;

    enum typeDAO {
        JDBC,
        Hibernate
    }

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }


    private UserService() {
        initialize();
        userDAO = userDAOFactory.createDAO();
    }


    public void initialize() {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("db.properties")) {
            properties.load(inputStream);
            typeDAO daotype = typeDAO.valueOf(properties.getProperty("daotype"));
            switch (daotype) {
                case JDBC:
                    userDAOFactory = new RealizationJdbcDAO();
                    break;
                case Hibernate:
                    userDAOFactory = new RealizationHibernateDAO();
                    break;
            }

        } catch (IOException e) {
            e.getStackTrace();
            new IOException();
        }
    }

    public static UserService getService() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    public void addUser(User user) {
        userDAO.add(user);
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void deleteUserById(int id) {
        userDAO.delete(id);
    }

    public User getUserById(int id) {
        return userDAO.getById(id);
    }
}
