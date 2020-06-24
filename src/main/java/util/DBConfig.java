package util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DBConfig {
    private String url;
    private String username;
    private String password;
    private String driver;
    private String dialect;
    private String hbm2ddl;
    private String show_sql;
    private String daotype;

    private static DBConfig dbConfig;

    public static DBConfig getDBConfig() {
        if (dbConfig == null) {
            dbConfig = new DBConfig();
        }
        return dbConfig;
    }

    private DBConfig() {}
}
