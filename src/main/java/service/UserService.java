package service;

import DAO.UserDAO;
import factory.UserDAOFactory;
import model.User;
import java.util.List;

public class UserService {
    private UserDAO<User> userDAO;
    private static UserService service;

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }


    private UserService() {
        userDAO = UserDAOFactory.createDAO();
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
