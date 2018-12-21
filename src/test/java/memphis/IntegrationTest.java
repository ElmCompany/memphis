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
        CallableStatement callableStatement = connection.prepareCall("{?=call xyz_not_found(?)}");
        try {
            callableStatement.execute();
            fail("should fail now");
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage(), startsWith("filename not found: "));
        }
    }

    @Test
    public void test_Parameters_WithNull_WithInteger() throws Exception {
        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:csv:classpath", "", "");
        CallableStatement callableStatement = connection.prepareCall("{call sp_test(?)}");
        ResultSet resultSet = callableStatement.executeQuery();

        assertThat(callableStatement.getInt("nullInt"), is(0));
        assertThat(callableStatement.wasNull(), is(true));

        assertThat(callableStatement.getDate("nullDate"), is(nullValue()));

        assertThat(callableStatement.getString("fistname"), is(notNullValue()));
        assertThat(callableStatement.wasNull(), is(false));

        while (resultSet.next()) {
            assertThat(resultSet.getInt("nullInt"), is(0));
            assertThat(resultSet.wasNull(), is(true));

            assertThat(resultSet.getDate("nullDate"), is(nullValue()));

            assertThat(resultSet.getString("fistname"), is(notNullValue()));
            assertThat(resultSet.wasNull(), is(false));
        }
    }
}
