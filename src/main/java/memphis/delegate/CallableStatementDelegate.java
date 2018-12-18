package memphis.delegate;

public class CallableStatementDelegate {

    private final String procName;

    public CallableStatementDelegate(String procName) {
        this.procName = procName;
    }

    public <T> T getValue(int parameterIndex) {
        return null;
    }

    public <T> T getValue(String parameterName) {
        return null;
    }

}
