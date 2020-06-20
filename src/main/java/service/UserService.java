package service;

import DAO.UserDAO;
import factory.RealizationHibernateDAO;
import factory.RealizationJdbcDAO;
import factory.UserDAOFactory;
import model.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        /* 1. InputStream inputStream = FetchData.class.getClassLoader().getResourceAsStream("db.properties");
        2. InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        3. InputStream inputStream = this.getClass().getResourceAsStream(“asdf.properties”);
        4. InputStream inputStream = getClass().getResource("images/logo.png");
        4. FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties");
        "src/main/resources/db.properties"
        */
        try (InputStream inputStream = UserService.class.getClassLoader().getResourceAsStream("db.properties");) {
            properties.load(inputStream);
            typeDAO daotype = typeDAO.valueOf(properties.getProperty("daotype"));
            System.out.println(daotype);
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
