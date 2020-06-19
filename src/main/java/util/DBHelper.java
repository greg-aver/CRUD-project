package util;

import DAO.UserHibernateDAO;
import org.hibernate.cfg.Configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Connection;

public class DBHelper {
    private static DBHelper dbHelper;
    private String url;
    private String username;
    private String password;
    private String driver;
    private String dialect;
    private String hbm2ddl;
    private String show_sql;

    private DBHelper() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            properties.load(fileInputStream);

            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            dialect = properties.getProperty("dialect");
            hbm2ddl = properties.getProperty("hbm2ddl");
            show_sql = properties.getProperty("show_sql");

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
            DriverManager.registerDriver((Driver) Class.forName(driver).newInstance());
            StringBuilder urlFull = new StringBuilder();
            urlFull.
                    append(url).
                    append("user=").
                    append(username).
                    append("&").
                    append("password=").
                    append(password);

            System.out.println("URL: " + urlFull + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }

    }

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserHibernateDAO.class);

        configuration.setProperty("hibernate.dialect", dialect)
                .setProperty("hibernate.connection.driver_class", driver)
                .setProperty("hibernate.connection.url", url)
                .setProperty("hibernate.connection.username", username)
                .setProperty("hibernate.connection.password", password)
                .setProperty("hibernate.show_sql", show_sql)
                .setProperty("hibernate.hbm2ddl.auto", hbm2ddl);

        return configuration;
    }

}
