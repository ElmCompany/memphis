package memphis.dummy;

import memphis.mock.MockedStoredProcedure;

public class DummyStoredProcedure implements MockedStoredProcedure {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public String getOutputParameter(int parameterIndex) {
        return null;
    }

    @Override
    public String getOutputParameter(String parameterName) {
        return null;
    }

    @Override
    public String getResultSetValue(int row, int columnIndex) {
        return null;
    }

    @Override
    public String getResultSetValue(int row, String columnLabel) {
        return null;
    }
}
