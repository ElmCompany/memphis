package memphis.mock.csv;

import memphis.mock.MockedStoredProcedure;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static memphis.Constants.JDBC_INDEX_OFFSET;
import static memphis.Util.apply;

public abstract class AbstractCsvStoredProcedure implements MockedStoredProcedure {

    private static final String NULL = "NULL";

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
        return apply(list.get(0).get(parameterIndex - JDBC_INDEX_OFFSET),
                this::trim,
                this::toNullIfRequired);
    }

    @Override
    public String getOutputParameter(String parameterName) {
        return apply(list.get(0).get(parameterName),
                this::trim,
                this::toNullIfRequired);
    }

    @Override
    public String getResultSetValue(int row, int columnIndex) {
        return apply(list.get(row - JDBC_INDEX_OFFSET).get(columnIndex - JDBC_INDEX_OFFSET),
                this::trim,
                this::toNullIfRequired);
    }

    @Override
    public String getResultSetValue(int row, String columnLabel) {
        return apply(list.get(row - JDBC_INDEX_OFFSET).get(columnLabel),
                this::trim,
                this::toNullIfRequired);
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

    private String toNullIfRequired(String input) {
        return NULL.equals(input) ? null : input;
    }
}
