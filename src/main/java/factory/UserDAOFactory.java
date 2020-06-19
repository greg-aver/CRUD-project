package factory;

import DAO.UserDAO;
import model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class UserDAOFactory {


    public UserDAO<User> createDAO() {
        UserDAO<User> userDAO = realizationDAO();
        return userDAO;
    }

    abstract public UserDAO<User> realizationDAO();
}
