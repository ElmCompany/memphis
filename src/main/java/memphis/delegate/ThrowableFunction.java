package memphis.delegate;

import java.sql.SQLException;

public interface ThrowableFunction<T, R> {

    R apply(T t) throws SQLException;

    static <T> ThrowableFunction<T, T> identity() {
        return t -> t;
    }
}
