package memphis.delegate;

import memphis.mock.Config;
import memphis.mock.MockSupport;
import memphis.mock.MockedStoredProcedure;

public class ResultSetDelegate {

    private int row;
    private final int total;
    private final MockedStoredProcedure mockedStoredProcedure;

    public ResultSetDelegate(String procName, Config config) {
        this.mockedStoredProcedure = MockSupport.getMock(procName, config);
        this.total = mockedStoredProcedure.getCount();
    }

    public String getValue(int columnIndex) {
        return mockedStoredProcedure.getResultSetValue(row, columnIndex);
    }

    public String getValue(String columnLabel) {
        return mockedStoredProcedure.getResultSetValue(row, columnLabel);
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
}
