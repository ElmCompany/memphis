package memphis;

import org.junit.Test;

import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class StatementTest {

    @Test
    public void testRegularStatement() throws Exception {
        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:csv:classpath", "", "");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select abc from efg");

        Integer integer = resultSet.getInt("some column");
        Date date = resultSet.getDate("some column");
        String string = resultSet.getString("some column");

        assertThat(integer, is(0));
        assertThat(date, is(nullValue()));
        assertThat(string, is(nullValue()));
    }

    @Test
    public void testPreparedStatement() throws Exception {

        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:csv:classpath", "", "");

        PreparedStatement statement = connection.prepareStatement("select abc from efg");

        ResultSet resultSet = statement.executeQuery();

        Integer integer = resultSet.getInt("some column");
        Date date = resultSet.getDate("some column");
        String string = resultSet.getString("some column");

        assertThat(integer, is(0));
        assertThat(date, is(nullValue()));
        assertThat(string, is(nullValue()));

    }
}
