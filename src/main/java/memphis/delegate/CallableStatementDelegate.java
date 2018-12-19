package memphis.delegate;

import memphis.mock.Config;
import memphis.mock.MockSupport;
import memphis.mock.MockedStoredProcedure;

import java.sql.SQLException;

public class CallableStatementDelegate {

    private boolean wasNull;
    private final MockedStoredProcedure mockedStoredProcedure;

    public CallableStatementDelegate(String procName, Config config) {
        mockedStoredProcedure = MockSupport.getMock(procName, config);
    }

    public <T> T getValue(int parameterIndex, ThrowableFunction<String, T> initializer,
                          String defaultValue) throws SQLException {
        String value = getValue(parameterIndex);
        return initializer.apply(value != null ? value : defaultValue);
    }

    public <T> T getValue(int parameterIndex,
                          ThrowableFunction<String, T> initializer) throws SQLException {
        String value = getValue(parameterIndex);
        return value != null ? initializer.apply(value) : null;
    }

    public <T> T getValue(String parameterName, ThrowableFunction<String, T> initializer,
                          String defaultValue) throws SQLException {
        String value = getValue(parameterName);
        return initializer.apply(value != null ? value : defaultValue);
    }

    public <T> T getValue(String parameterName,
                          ThrowableFunction<String, T> initializer) throws SQLException {
        String value = getValue(parameterName);
        return value != null ? initializer.apply(value) : null;
    }

    public boolean wasNull() {
        return wasNull;
    }

    // ---

    private String getValue(int parameterIndex) {
        String value = mockedStoredProcedure.getOutputParameter(parameterIndex);
        this.wasNull = value == null;
        return value;
    }

    private String getValue(String parameterName) {
        String value = mockedStoredProcedure.getOutputParameter(parameterName);
        this.wasNull = value == null;
        return value;
    }
}
