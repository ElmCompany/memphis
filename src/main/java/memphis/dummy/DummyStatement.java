package memphis.dummy;

import memphis.CallableStatement;
import memphis.Connection;
import memphis.mock.Config;
import memphis.mock.MockStrategy;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DummyStatement extends CallableStatement {

    private static Config DUMMY_CONFIG = Config.of(null, MockStrategy.DUMMY);

    private DummyResultSet resultSet = new DummyResultSet(this, null, DUMMY_CONFIG);

    public DummyStatement(Connection connection) {
        super(connection, null, DUMMY_CONFIG);
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return resultSet;
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return resultSet;
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return resultSet;
    }
}
