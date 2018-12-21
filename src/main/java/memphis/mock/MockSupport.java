package memphis.mock;

import memphis.mock.csv.ClassPathCsvStoredProcedure;
import memphis.mock.csv.FileSystemCsvStoredProcedure;

import java.util.Map;

public class MockSupport {

    public static MockedStoredProcedure getMock(String procName, Map<String, Object> capturedParameters, Config config) {

        if (config.getMockStrategy() == MockStrategy.CSV_CLASSPATH) {
            return new ClassPathCsvStoredProcedure("memphis/" + procName + "/" +
                    determineCsvFile(procName, capturedParameters));

        } else if (config.getMockStrategy() == MockStrategy.CSV_FILESYSTEM) {
            return new FileSystemCsvStoredProcedure(config.getBaseFsDir() + "/" + procName + "/" +
                    determineCsvFile(procName, capturedParameters));
        } else {
            throw new IllegalArgumentException("illegal mock strategy: '" + config.getMockStrategy() +
                    "', possible values are csv, filesystem");
        }
    }

    private static String determineCsvFile(String procName, Map<String, Object> capturedParameters) {

        String indexFile = "memphis/" + procName + "/index.csv";

        // TODO find records in index.csv that most matches the supplied parameters

        return "2.csv";
    }
}
