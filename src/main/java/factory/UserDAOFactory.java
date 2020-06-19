package factory;

import DAO.UserDAO;
import model.User;

public abstract class UserDAOFactory {



    abstract public UserDAO<User> realizationDAO();
}
