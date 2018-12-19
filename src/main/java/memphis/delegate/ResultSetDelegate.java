package memphis.delegate;

import memphis.mock.Config;
import memphis.mock.MockSupport;
import memphis.mock.MockedStoredProcedure;

import java.sql.SQLException;

public class ResultSetDelegate {

    private int row;
    private boolean wasNull;

    private final int total;
    private final MockedStoredProcedure mockedStoredProcedure;

    public ResultSetDelegate(String procName, Config config) {
        this.mockedStoredProcedure = MockSupport.getMock(procName, config);
        this.total = mockedStoredProcedure.getCount();
    }

    public <T> T getValue(int columnIndex, ThrowableFunction<String, T> initializer,
                          String defaultValue) throws SQLException {
        String value = getValue(columnIndex);
        return initializer.apply(value != null ? value : defaultValue);
    }

    public <T> T getValue(int columnIndex,
                          ThrowableFunction<String, T> initializer) throws SQLException {
        String value = getValue(columnIndex);
        return value != null ? initializer.apply(value) : null;
    }

    public <T> T getValue(String columnLabel, ThrowableFunction<String, T> initializer,
                          String defaultValue) throws SQLException {
        String value = getValue(columnLabel);
        return initializer.apply(value != null ? value : defaultValue);
    }

    public <T> T getValue(String columnLabel,
                          ThrowableFunction<String, T> initializer) throws SQLException {
        String value = getValue(columnLabel);
        return value != null ? initializer.apply(value) : null;
    }

    public boolean wasNull() {
        return wasNull;
    }

    public boolean next() {
        if (row < total) {
            row++;
            return true;
        } else {
            return false;
        }
    }

    public int getRow() {
        return row;
    }

    // private
    private String getValue(int columnIndex) {
        String value = mockedStoredProcedure.getResultSetValue(row, columnIndex);
        this.wasNull = value == null;
        return value;
    }

    private String getValue(String columnLabel) {
        String value = mockedStoredProcedure.getResultSetValue(row, columnLabel);
        this.wasNull = value == null;
        return value;
    }
}
