package util;

import DAO.UserHibernateDAO;
import org.hibernate.cfg.Configuration;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Connection;

public class DBHelper {
    private static DBHelper dbHelper;
    private DBConfig config = new DBConfig();

    private DBHelper() {
        Properties properties = new Properties();

        try (InputStream inputStream = DBHelper.class.getClassLoader().getResourceAsStream("db.properties");) {
            properties.load(inputStream);

            config.setUrl(properties.getProperty("url"));
            config.setUsername(properties.getProperty("username"));
            config.setPassword(properties.getProperty("password"));
            config.setDriver(properties.getProperty("driver"));
            config.setDialect(properties.getProperty("dialect"));
            config.setHbm2ddl(properties.getProperty("hbm2ddl"));
            config.setShow_sql(properties.getProperty("show_sql"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static DBHelper getDbHelper() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public Connection getConnection() {
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

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
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
