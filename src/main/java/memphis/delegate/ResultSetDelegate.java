package memphis.delegate;

public class ResultSetDelegate {

    private int row;
    private int column;

    private int total;
    private final String procName;

    public ResultSetDelegate(String procName) {
        this.procName = procName;
    }

    public <T> T getValue(int columnIndex) {
        return null;
    }

    public <T> T getValue(String columnLabel) {
        return null;
    }

    public boolean next() {
        return row++ < total;
    }

    public int getRow() {
        return row;
    }

    public boolean wasNull() {
        return false;
    }
}
