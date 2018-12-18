package memphis.mock.csv;

import memphis.mock.MockedStoredProcedure;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public abstract class AbstractCsvStoredProcedure implements MockedStoredProcedure {

    private final static int JDBC_INDEX_OFFSET = 1;

    private final List<CSVRecord> list;

    AbstractCsvStoredProcedure(String filename) {
        list = load(filename);
    }

    protected abstract Reader getReader(String filename) throws IOException;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getOutputParameter(int parameterIndex) {
        return trim(list.get(0).get(parameterIndex - JDBC_INDEX_OFFSET));
    }

    @Override
    public String getOutputParameter(String parameterName) {
        return trim(list.get(0).get(parameterName));
    }

    @Override
    public String getResultSetValue(int row, int columnIndex) {
        return trim(list.get(row).get(columnIndex - JDBC_INDEX_OFFSET));
    }

    @Override
    public String getResultSetValue(int row, String columnLabel) {
        return trim(list.get(row).get(columnLabel));
    }

    private List<CSVRecord> load(String filename) {

        try (Reader fileReader = getReader(filename)) {

            return CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(fileReader)
                    .getRecords();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String trim(String input) {
        return input != null ? input.trim() : null;
    }
}
