package memphis;

import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class IntegrationTest {

    @Test
    public void test_Parameters() throws Exception {
        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:csv:classpath", "", "");
        CallableStatement callableStatement = connection.prepareCall("{call sp_test(?)}");
        callableStatement.execute();

        assertThat(callableStatement.getString("fistname"), is("abdullah"));
        assertThat(callableStatement.getString(2), is("mohammad"));
        assertThat(callableStatement.getInt("age"), is(5));
    }

    @Test
    public void test_ResultSet() throws Exception {
        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:csv:classpath", "", "");
        CallableStatement callableStatement = connection.prepareCall("{call sp_test(?)}");
        ResultSet resultSet = callableStatement.executeQuery();

        assertThat(resultSet.next(), is(equalTo(true)));

        assertThat(resultSet.getString("fistname"), is("abdullah"));
        assertThat(resultSet.getString(2), is("mohammad"));
        assertThat(resultSet.getInt("age"), is(5));

        assertThat(resultSet.next(), is(equalTo(true)));

        assertThat(resultSet.getString("fistname"), is("farida"));
        assertThat(resultSet.getString(2), is("mohammad"));
        assertThat(resultSet.getInt("age"), is(3));

        assertThat(resultSet.next(), is(equalTo(false)));
    }

    @Test
    public void test_invalidSpName() throws Exception {
        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:csv:classpath", "", "");
        try {
            connection.prepareCall("{?=call xyz_not_found(?)}");
            fail("should fail now");
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage(), startsWith("filename not found: "));
        }
    }
}
