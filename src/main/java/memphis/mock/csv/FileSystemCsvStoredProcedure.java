package memphis.mock.csv;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Slf4j
public class FileSystemCsvStoredProcedure extends AbstractCsvStoredProcedure {

    public FileSystemCsvStoredProcedure(String filename) {
        super(filename);
    }

    @Override
    protected Reader getReader(String filename) throws IOException {
        log.debug("loading csv file from filesystem: {}", filename);
        return new FileReader(filename);
    }
}
