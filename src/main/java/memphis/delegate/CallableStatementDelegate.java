package memphis.delegate;

import memphis.mock.Config;
import memphis.mock.MockSupport;
import memphis.mock.MockedStoredProcedure;

public class CallableStatementDelegate extends AbstractDelegate {

    private boolean wasNull;
    private final MockedStoredProcedure mockedStoredProcedure;

    public CallableStatementDelegate(String procName, Config config) {
        mockedStoredProcedure = MockSupport.getMock(procName, config);
    }

    public boolean wasNull() {
        return wasNull;
    }

    protected String getValue(int parameterIndex) {
        String value = mockedStoredProcedure.getOutputParameter(parameterIndex);
        this.wasNull = value == null;
        return value;
    }

    protected String getValue(String parameterName) {
        String value = mockedStoredProcedure.getOutputParameter(parameterName);
        this.wasNull = value == null;
        return value;
    }
}
