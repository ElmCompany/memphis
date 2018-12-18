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
        Connection connection = DriverManager.getConnection("jdbc:memphis:", "", "");

        assertThat(connection, instanceOf(memphis.Connection.class));
    }

    @Test
    public void testGetCallableStatement() throws Exception {
        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:", "", "");
        CallableStatement callableStatement = connection.prepareCall("{call sp_name(?, ?)}");

        assertThat(callableStatement, instanceOf(memphis.CallableStatement.class));
    }

    @Test
    public void testGetResultSet() throws Exception {
        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:", "", "");
        CallableStatement callableStatement = connection.prepareCall("{call sp_name(?, ?)}");
        ResultSet resultSet = callableStatement.executeQuery();

        assertThat(resultSet, instanceOf(memphis.ResultSet.class));
    }
}