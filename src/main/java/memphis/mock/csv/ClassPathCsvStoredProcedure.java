package memphis.mock.csv;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class ClassPathCsvStoredProcedure extends AbstractCsvStoredProcedure {

    public ClassPathCsvStoredProcedure(String filename) {
        super(filename);
    }

    @Override
    protected Reader getReader(String filename) {
        log.debug("loading csv file from classpath: {}", filename);
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            throw new RuntimeException("filename not found: " + filename);
        }
        return new InputStreamReader(inputStream, UTF_8);
    }
}
