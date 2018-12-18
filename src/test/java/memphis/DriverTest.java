package memphis;

import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class DriverTest {

    @Test
    public void testGetConnection() throws Exception {
        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:csv:classpath", "", "");

        assertThat(connection, instanceOf(memphis.Connection.class));
    }

    @Test
    public void testGetCallableStatement() throws Exception {
        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:csv:classpath", "", "");
        CallableStatement callableStatement = connection.prepareCall("{call sp_test(?, ?)}");

        assertThat(callableStatement, instanceOf(memphis.CallableStatement.class));
    }

    @Test
    public void testGetResultSet() throws Exception {
        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:csv:classpath", "", "");
        CallableStatement callableStatement = connection.prepareCall("{call sp_test(?, ?)}");
        ResultSet resultSet = callableStatement.executeQuery();

        assertThat(resultSet, instanceOf(memphis.ResultSet.class));
    }
}