package factory;

import DAO.UserDAO;
import model.User;
import util.DBConfig;
import util.PropertyReader;

public abstract class UserDAOFactory {

    enum typeDAO {
        JDBC,
        Hibernate
    }

    public static UserDAO<User> createDAO() {
        PropertyReader.read();
        typeDAO daotype = typeDAO.valueOf(DBConfig.getDBConfig().getDaotype());
        UserDAOFactory userDaoFactory = null;
        switch (daotype) {
            case JDBC:
                userDaoFactory = new RealizationJdbcDAO();
                break;
            case Hibernate:
                userDaoFactory = new RealizationHibernateDAO();
                break;
        }
        return userDaoFactory.realizationDAO();
    }

    abstract public UserDAO<User> realizationDAO();
}
