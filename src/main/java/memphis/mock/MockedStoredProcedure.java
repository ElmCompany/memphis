package memphis.mock;

public interface MockedStoredProcedure {

    int getCount();

    String getOutputParameter(int parameterIndex);

    String getOutputParameter(String parameterName);

    String getResultSetValue(int row, int columnIndex);

    String getResultSetValue(int row, String columnLabel);
}
