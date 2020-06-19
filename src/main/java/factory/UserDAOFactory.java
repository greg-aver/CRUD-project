package factory;

import DAO.UserDAO;
import model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class UserDAOFactory {

   enum DAO {
       JDBC,
       Hibernate
   }

    public void createDAO() {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("db.properties")) {
            properties.load(inputStream);
            DAO daotype = DAO.valueOf(properties.getProperty("daotype"));
            switch (daotype) {
                case JDBC:
                    break;
                case Hibernate:
                    break;
            }

            String qe = String.valueOf(DAO.Hibernate);
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    abstract public UserDAO<User> realizationDAO();
}
