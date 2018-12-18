package memphis;

import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class IntegrationTest {

    @Test
    public void test1() throws Exception {
        Class.forName("memphis.Driver");
        Connection connection = DriverManager.getConnection("jdbc:memphis:csv:classpath", "", "");
        CallableStatement callableStatement = connection.prepareCall("{call sp_test(?)}");
        callableStatement.execute();

        String fistname = callableStatement.getString("fistname");
        String lastname = callableStatement.getString(2);
        Integer age = callableStatement.getInt("age");

        assertThat(fistname, is("abdullah"));
        assertThat(lastname, is("mohammad"));
        assertThat(age, is(5));
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
