package limehrm.util.db;

import limehrm.Limehrm;
import limehrm.util.LoggerUtil;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.Schema;
import org.jooq.impl.DSL;
import org.jooq.meta.postgres.information_schema.InformationSchema;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DbUtil {
    private static final LoggerUtil logger = LoggerUtil.getLogger("DbUtil");
    private final Connection connection;
    private final DSLContext create;
    
    private final String jdbcUrl;
    private final String user;
    private final String password;
    
    public DbUtil(String dBMSName, String dBMSDriverName, String url, String databaseName, String user, String password) throws SQLException {
        String jdbcUrl = String.format("jdbc:%s://%s/%s", dBMSDriverName, url, databaseName);
    
        connection = DriverManager.getConnection(jdbcUrl, user, password);
    
        create = DSL.using(connection, SQLDialect.valueOf(dBMSName.toUpperCase()));
        
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
    }
    
    public void runSql(String relativeFilePath) throws FileNotFoundException {
        ScriptRunner sr = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + relativeFilePath));
        sr.runScript(reader);
    }
    
    public DSLContext getCreate() {
        return create;
    }
    
    public String getJdbcUrl() {
        return jdbcUrl;
    }
    
    public String getUser() {
        return user;
    }
    
    public String getPassword() {
        return password;
    }
}
