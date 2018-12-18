package memphis;

import java.util.regex.Pattern;

public class Util {

    private static Pattern PROC_NAME = Pattern.compile(".*call(.*?)[(}].*");

    public static String extractProcName(String sql) {
        return PROC_NAME.matcher(sql).replaceAll("$1").trim();
    }
}
