package memphis.delegate;

import memphis.mock.Config;
import memphis.mock.MockSupport;
import memphis.mock.MockedStoredProcedure;

public class CallableStatementDelegate {

    private final MockedStoredProcedure mockedStoredProcedure;

    public CallableStatementDelegate(String procName, Config config) {
        mockedStoredProcedure = MockSupport.getMock(procName, config);
    }

    public String getValue(int parameterIndex) {
        return mockedStoredProcedure.getOutputParameter(parameterIndex);
    }

    public String getValue(String parameterName) {
        return mockedStoredProcedure.getOutputParameter(parameterName);
    }
}
