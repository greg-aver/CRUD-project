package factory;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import model.User;

public class RealizationHibernateDAO extends UserDAOFactory {
    @Override
    public UserDAO<User> realizationDAO() {
        return new UserHibernateDAO();
    }
}
