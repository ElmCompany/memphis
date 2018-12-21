package memphis.delegate;

import memphis.mock.MockedStoredProcedure;

public class ResultSetDelegate extends AbstractDelegate {

    private int row;
    private boolean wasNull;

    private final int total;
    private final MockedStoredProcedure mockedStoredProcedure;

    public ResultSetDelegate(MockedStoredProcedure mockedStoredProcedure) {
        this.mockedStoredProcedure = mockedStoredProcedure;
        this.total = mockedStoredProcedure.getCount();
    }

    public boolean wasNull() {
        return wasNull;
    }

    protected String getValue(int columnIndex) {
        String value = mockedStoredProcedure.getResultSetValue(row, columnIndex);
        this.wasNull = value == null;
        return value;
    }

    protected String getValue(String columnLabel) {
        String value = mockedStoredProcedure.getResultSetValue(row, columnLabel);
        this.wasNull = value == null;
        return value;
    }

    public int getRow() {
        return row;
    }

    public boolean next() {
        if (row < total) {
            row++;
            return true;
        }
        return false;
    }
}
