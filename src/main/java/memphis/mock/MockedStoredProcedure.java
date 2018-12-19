package memphis.mock;

public interface MockedStoredProcedure {

    int getCount();

    /**
     * one-based index
     */
    String getOutputParameter(int parameterIndex);

    /**
     * one-based index
     */
    String getOutputParameter(String parameterName);

    /**
     * one-based index
     */
    String getResultSetValue(int row, int columnIndex);

    /**
     * one-based index
     */
    String getResultSetValue(int row, String columnLabel);
}
