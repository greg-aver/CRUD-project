package service;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import model.User;
import util.DBConfig;

import java.util.List;

import DAO.UserJdbcDAO;

public class UserService {
    private UserDAO<User> userDAO;
    private static UserService service;
    private UserJdbcDAO userJdbcDAO = UserJdbcDAO.getUserJdbcDAO(DBConfig.getMysqlConnection());
    private UserHibernateDAO userHibernateDAO = new UserHibernateDAO();
    public List<User> getAllUsers() {
        return userJdbcDAO.getAll();
    }

    private UserService() {
//        userDAO = userJdbcDAO;
        userDAO = userHibernateDAO;
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
