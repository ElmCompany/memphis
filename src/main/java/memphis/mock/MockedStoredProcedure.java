package memphis.mock;

public interface MockedStoredProcedure {

    int getCount();

    /**
     * one-based index. result can be null.
     */
    String getOutputParameter(int parameterIndex);

    /**
     * one-based index. result can be null.
     */
    String getOutputParameter(String parameterName);

    /**
     * one-based index. result can be null.
     */
    String getResultSetValue(int row, int columnIndex);

    /**
     * one-based index. result can be null.
     */
    String getResultSetValue(int row, String columnLabel);
}
