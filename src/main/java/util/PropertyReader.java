package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static void read() {
        DBConfig config = DBConfig.getDBConfig();
        Properties properties = new Properties();
        try (InputStream inputStream = DBConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(inputStream);

            config.setUrl(properties.getProperty("url"));
            config.setUsername(properties.getProperty("username"));
            config.setPassword(properties.getProperty("password"));
            config.setShow_sql(properties.getProperty("show_sql"));
            config.setDialect(properties.getProperty("dialect"));
            config.setDriver(properties.getProperty("driver"));
            config.setHbm2ddl(properties.getProperty("hbm2ddl"));
            config.setDaotype(properties.getProperty("daotype"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
