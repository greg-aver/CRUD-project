package factory;

import DAO.UserDAO;
import DAO.UserJdbcDAO;
import model.User;

public class RealizationJdbcDAO extends UserDAOFactory {
    @Override
    public UserDAO<User> realizationDAO() {
        return new UserJdbcDAO();
    }
}
