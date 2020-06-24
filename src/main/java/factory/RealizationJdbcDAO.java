package factory;

import DAO.UserDAO;
import DAO.UserJdbcDAO;
import model.User;
import util.DBHelper;

public class RealizationJdbcDAO extends UserDAOFactory {
    @Override
    public UserDAO<User> realizationDAO() {
        return UserJdbcDAO.getUserJdbcDAO(DBHelper.getConnection());
    }
}
