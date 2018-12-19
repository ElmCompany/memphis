package memphis;

import memphis.mock.MockStrategy;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Util {

    private static Pattern PROC_NAME = Pattern.compile(".*call(.*?)[(}].*");

    public static String extractProcName(String sql) {
        return PROC_NAME.matcher(sql).replaceAll("$1").trim();
    }

    public static String extractBaseFileSystem(String jdbcUrl) {
        String jdbcUrlStart = MockStrategy.CSV_FILESYSTEM.getJdbcUrlStart();

        if (!jdbcUrl.contains(jdbcUrlStart)) {
            return null;
        }

        return jdbcUrl.replace(jdbcUrlStart, "");
    }

    @SafeVarargs
    public static <T> T apply(T input, UnaryOperator<T>... operators) {
        T output = input;
        for (UnaryOperator<T> function : operators) {
            output = function.apply(output);
        }
        return output;
    }
}
