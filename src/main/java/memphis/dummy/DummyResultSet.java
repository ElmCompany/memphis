package memphis.dummy;

import memphis.CallableStatement;
import memphis.ResultSet;
import memphis.delegate.ResultSetDelegate;
import memphis.mock.Config;

public class DummyResultSet extends ResultSet {

    public DummyResultSet(CallableStatement callableStatement, String procName, Config config) {
        super(callableStatement, procName, config);

        this.delegate = new ResultSetDelegate(procName, config) {
            @Override
            protected String getValue(int columnIndex) {
                return null;
            }

            @Override
            protected String getValue(String columnLabel) {
                return null;
            }
        };
    }
}
