package memphis.mock;

import java.util.Arrays;

import static memphis.Constants.JDBC_START;

public enum MockStrategy {

    CSV_CLASSPATH(JDBC_START + "csv:classpath"),
    CSV_FILESYSTEM(JDBC_START + "csv:file://");

    private String jdbcUrlStart;

    MockStrategy(String jdbcUrlStart) {
        this.jdbcUrlStart = jdbcUrlStart;
    }

    public String getJdbcUrlStart() {
        return jdbcUrlStart;
    }

    public static MockStrategy from(String jdbcUrl) {
        return Arrays.stream(MockStrategy.values())
                .filter(it -> jdbcUrl.startsWith(it.jdbcUrlStart))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("invalid jdbc url" + jdbcUrl));
    }
}
