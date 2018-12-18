package memphis.mock.csv;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ClassPathCsvStoredProcedure extends AbstractCsvStoredProcedure {

    public ClassPathCsvStoredProcedure(String filename) {
        super(filename);
    }

    @Override
    protected Reader getReader(String filename) {
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            throw new RuntimeException("filename not found: " + filename);
        }
        return new InputStreamReader(inputStream, UTF_8);
    }
}
