package memphis.delegate;

import java.sql.SQLException;

public abstract class AbstractDelegate {

    protected abstract String getValue(int index);

    protected abstract String getValue(String name);

    public abstract boolean wasNull();

    public <T> T getValue(int index, ThrowableFunction<String, T> initializer,
                          String defaultValue) throws SQLException {
        String value = getValue(index);
        return initializer.apply(value != null ? value : defaultValue);
    }

    public <T> T getValue(int index,
                          ThrowableFunction<String, T> initializer) throws SQLException {
        String value = getValue(index);
        return value != null ? initializer.apply(value) : null;
    }

    public <T> T getValue(String name, ThrowableFunction<String, T> initializer,
                          String defaultValue) throws SQLException {
        String value = getValue(name);
        return initializer.apply(value != null ? value : defaultValue);
    }

    public <T> T getValue(String name,
                          ThrowableFunction<String, T> initializer) throws SQLException {
        String value = getValue(name);
        return value != null ? initializer.apply(value) : null;
    }
}
