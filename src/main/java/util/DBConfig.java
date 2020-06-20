package util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class DBConfig {
    private String url;
    private String username;
    private String password;
    private String driver;
    private String dialect;
    private String hbm2ddl;
    private String show_sql;
}
