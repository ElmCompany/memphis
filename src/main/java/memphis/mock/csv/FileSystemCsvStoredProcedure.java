package memphis.mock.csv;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileSystemCsvStoredProcedure extends AbstractCsvStoredProcedure {

    public FileSystemCsvStoredProcedure(String filename) {
        super(filename);
    }

    @Override
    protected Reader getReader(String filename) throws IOException {
        return new FileReader(filename);
    }
}
