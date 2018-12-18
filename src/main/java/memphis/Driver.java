package memphis;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

import static memphis.Constants.JDBC_START;

public class Driver implements java.sql.Driver {

    private static final Driver INSTANCE = new Driver();
    private static volatile boolean registered;

    static {
        load();
    }

    public java.sql.Connection connect(String url, Properties info) throws SQLException {
        return acceptsURL(url) ? new Connection() : null;
    }

    public boolean acceptsURL(String url) throws SQLException {
        return url.startsWith(JDBC_START);
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    public int getMajorVersion() {
        return 0;
    }

    public int getMinorVersion() {
        return 0;
    }

    public boolean jdbcCompliant() {
        return false;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    private static synchronized java.sql.Driver load() {
        try {
            if (!registered) {
                registered = true;
                DriverManager.registerDriver(INSTANCE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return INSTANCE;
    }
}
