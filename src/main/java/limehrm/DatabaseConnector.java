package limehrm;

import limehrm.util.db.DbUtil;

import java.sql.SQLException;

public class DatabaseConnector {
    public static DbUtil dbUtil;
    
    static {
        try {
            dbUtil = new DbUtil("postgres", "postgresql", "localhost:5432", "postgres", "postgres", "postgres");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
