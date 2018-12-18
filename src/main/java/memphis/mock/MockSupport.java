package memphis.mock;

import memphis.mock.csv.ClassPathCsvStoredProcedure;
import memphis.mock.csv.FileSystemCsvStoredProcedure;

public class MockSupport {

    public static MockedStoredProcedure getMock(String procName, Config config) {

        if (config.getMockStrategy() == MockStrategy.CSV_CLASSPATH) {
            return new ClassPathCsvStoredProcedure("memphis/" + procName + "/" + decideWhichFile(procName));

        } else if (config.getMockStrategy() == MockStrategy.CSV_FILESYSTEM) {
            return new FileSystemCsvStoredProcedure(config.getBaseFsDir() + "/" + procName + "/" + decideWhichFile(procName));

        } else {
            throw new IllegalArgumentException("illegal mock strategy: '" + config.getMockStrategy() +
                    "', possible values are csv, filesystem");
        }
    }

    // TODO
    // decide should be done by parsing index.csv
    private static String decideWhichFile(String procName) {
        return "2.csv";
    }
}
