package memphis.mock.csv;

import memphis.mock.MockedStoredProcedure;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CsvStoredProcedureTest {

    @Test
    public void testLoad() {
        MockedStoredProcedure csvStoredProcedure = new ClassPathCsvStoredProcedure("memphis/sp_test/2.csv");

        int total = csvStoredProcedure.getCount();

        assertThat(total, is(2));

        // -- as output params
        assertThat(csvStoredProcedure.getOutputParameter(1), is("abdullah"));
        assertThat(csvStoredProcedure.getOutputParameter("fistname"), is("abdullah"));

        assertThat(csvStoredProcedure.getOutputParameter(2), is("mohammad"));
        assertThat(csvStoredProcedure.getOutputParameter("lastname"), is("mohammad"));

        assertThat(csvStoredProcedure.getOutputParameter(3), is("5"));
        assertThat(csvStoredProcedure.getOutputParameter("age"), is("5"));

        // --- as result set
        int row = 1;
        assertThat(csvStoredProcedure.getResultSetValue(row, 1), is("abdullah"));
        assertThat(csvStoredProcedure.getResultSetValue(row, "fistname"), is("abdullah"));

        assertThat(csvStoredProcedure.getResultSetValue(row, 2), is("mohammad"));
        assertThat(csvStoredProcedure.getResultSetValue(row, "lastname"), is("mohammad"));

        assertThat(csvStoredProcedure.getResultSetValue(row, 3), is("5"));
        assertThat(csvStoredProcedure.getResultSetValue(row, "age"), is("5"));

        row = 2;
        assertThat(csvStoredProcedure.getResultSetValue(row, 1), is("farida"));
        assertThat(csvStoredProcedure.getResultSetValue(row, "fistname"), is("farida"));

        assertThat(csvStoredProcedure.getResultSetValue(row, 2), is("mohammad"));
        assertThat(csvStoredProcedure.getResultSetValue(row, "lastname"), is("mohammad"));

        assertThat(csvStoredProcedure.getResultSetValue(row, 3), is("3"));
        assertThat(csvStoredProcedure.getResultSetValue(row, "age"), is("3"));
    }

    @Test
    public void testLoad_withEmptyField() {
        MockedStoredProcedure csvStoredProcedure = new ClassPathCsvStoredProcedure("test1.csv");

        int total = csvStoredProcedure.getCount();

        assertThat(total, is(1));

        // -- as output params
        assertThat(csvStoredProcedure.getOutputParameter(1), is("abdullah"));
        assertThat(csvStoredProcedure.getOutputParameter("fistname"), is("abdullah"));

        assertThat(csvStoredProcedure.getOutputParameter(2), is(""));
        assertThat(csvStoredProcedure.getOutputParameter("lastname"), is(""));

        assertThat(csvStoredProcedure.getOutputParameter(3), is("5"));
        assertThat(csvStoredProcedure.getOutputParameter("age"), is("5"));
    }

}