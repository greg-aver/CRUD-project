package util;

import DAO.UserHibernateDAO;
import com.mysql.cj.jdbc.Driver;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    public static Connection getConnection() {
        final DBConfig config = DBConfig.getDBConfig();
        try {
            DriverManager.registerDriver((Driver) Class.forName(config.getDriver()).newInstance());
            StringBuilder urlFull = new StringBuilder();
            urlFull.
                    append(config.getUrl()).
                    append("&").
                    append("user=").
                    append(config.getUsername()).
                    append("&").
                    append("password=").
                    append(config.getPassword());

            System.out.println("URL: " + urlFull + "\n");
            Connection connection = DriverManager.getConnection(urlFull.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }

    }

    public static Configuration getConfiguration() {
        final DBConfig config = DBConfig.getDBConfig();
        final Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserHibernateDAO.class);

        configuration.setProperty("hibernate.dialect", config.getDialect())
                .setProperty("hibernate.connection.driver_class", config.getDriver())
                .setProperty("hibernate.connection.url", config.getUrl())
                .setProperty("hibernate.connection.username", config.getUsername())
                .setProperty("hibernate.connection.password", config.getPassword())
                .setProperty("hibernate.show_sql", config.getShow_sql())
                .setProperty("hibernate.hbm2ddl.auto", config.getHbm2ddl());

        return configuration;
    }
}
