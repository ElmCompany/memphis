package memphis;

import memphis.base.AbstractCallableStatement;
import memphis.delegate.CallableStatementDelegate;
import memphis.delegate.ThrowableFunction;
import memphis.mock.Config;
import memphis.mock.MockSupport;
import memphis.mock.MockedStoredProcedure;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CallableStatement extends AbstractCallableStatement {

    private final Config config;
    private final String procName;

    private ResultSet resultSet;
    private CallableStatementDelegate delegate;
    private Map<String, Object> capturedParameters = new HashMap<>();

    public CallableStatement(Connection connection, String procName, Config config) {
        super(connection);
        this.config = config;
        this.procName = procName;
    }

    private void doBeforeExecute() {
        MockedStoredProcedure mock = MockSupport.getMock(procName, capturedParameters, config);
        this.resultSet = new ResultSet(this, mock);
        this.delegate = new CallableStatementDelegate(mock);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {

    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {

    }

    @Override
    public boolean wasNull() throws SQLException {
        return delegate.wasNull();
    }

    @Override
    public String getString(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, ThrowableFunction.identity());
    }

    @Override
    public boolean getBoolean(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, Boolean::valueOf, "false");
    }

    @Override
    public byte getByte(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, Byte::valueOf, "0");
    }

    @Override
    public short getShort(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, Short::valueOf, "0");
    }

    @Override
    public int getInt(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, Integer::valueOf, "0");
    }

    @Override
    public long getLong(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, Long::valueOf, "0");
    }

    @Override
    public float getFloat(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, Float::valueOf, "0");
    }

    @Override
    public double getDouble(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, Double::valueOf, "0");
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        return delegate.getValue(parameterIndex, BigDecimal::new);
    }

    @Override
    public byte[] getBytes(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, String::getBytes);
    }

    @Override
    public Date getDate(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, Date::valueOf);
    }

    @Override
    public Time getTime(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, Time::valueOf);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, Timestamp::valueOf);
    }

    @Override
    public Object getObject(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, ThrowableFunction.identity());
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, BigDecimal::new);
    }

    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        return delegate.getValue(parameterIndex, ThrowableFunction.identity());
    }

    @Override
    public Ref getRef(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("getRef");
    }

    @Override
    public Blob getBlob(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, it -> new SerialBlob(it.getBytes()));
    }

    @Override
    public Clob getClob(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, it -> new SerialClob(it.toCharArray()));
    }

    @Override
    public Array getArray(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("getArray");
    }

    @Override
    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return delegate.getValue(parameterIndex, Date::valueOf);
    }

    @Override
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return delegate.getValue(parameterIndex, Time::valueOf);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        return delegate.getValue(parameterIndex, Timestamp::valueOf);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {

    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {

    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {

    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {

    }

    @Override
    public URL getURL(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, it -> {
            try {
                return new URL(it);
            } catch (MalformedURLException e) {
                throw new SQLException(e);
            }
        });
    }

    @Override
    public void setURL(String parameterName, URL val) throws SQLException {

    }

    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {

    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {

    }

    @Override
    public void setByte(String parameterName, byte x) throws SQLException {

    }

    @Override
    public void setShort(String parameterName, short x) throws SQLException {

    }

    @Override
    public void setInt(String parameterName, int x) throws SQLException {

    }

    @Override
    public void setLong(String parameterName, long x) throws SQLException {

    }

    @Override
    public void setFloat(String parameterName, float x) throws SQLException {

    }

    @Override
    public void setDouble(String parameterName, double x) throws SQLException {

    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {

    }

    @Override
    public void setString(String parameterName, String x) throws SQLException {

    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {

    }

    @Override
    public void setDate(String parameterName, Date x) throws SQLException {

    }

    @Override
    public void setTime(String parameterName, Time x) throws SQLException {

    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {

    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {

    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {

    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {

    }

    @Override
    public void setObject(String parameterName, Object x) throws SQLException {

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {

    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {

    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {

    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {

    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {

    }

    @Override
    public String getString(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, ThrowableFunction.identity());
    }

    @Override
    public boolean getBoolean(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, Boolean::valueOf, "false");
    }

    @Override
    public byte getByte(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, Byte::valueOf, "0");
    }

    @Override
    public short getShort(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, Short::valueOf, "0");
    }

    @Override
    public int getInt(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, Integer::valueOf, "0");
    }

    @Override
    public long getLong(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, Long::valueOf, "0");
    }

    @Override
    public float getFloat(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, Float::valueOf, "0");
    }

    @Override
    public double getDouble(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, Double::valueOf, "0");
    }

    @Override
    public byte[] getBytes(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, String::getBytes);
    }

    @Override
    public Date getDate(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, Date::valueOf);
    }

    @Override
    public Time getTime(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, Time::valueOf);
    }

    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, Timestamp::valueOf);
    }

    @Override
    public Object getObject(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, ThrowableFunction.identity());
    }

    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, BigDecimal::new);
    }

    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        return delegate.getValue(parameterName, ThrowableFunction.identity());
    }

    @Override
    public Ref getRef(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("getRef");
    }

    @Override
    public Blob getBlob(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, it -> new SerialBlob(it.getBytes()));
    }

    @Override
    public Clob getClob(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, it -> new SerialClob(it.toCharArray()));
    }

    @Override
    public Array getArray(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("getArray");
    }

    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return delegate.getValue(parameterName, Date::valueOf);
    }

    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return delegate.getValue(parameterName, Time::valueOf);
    }

    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return delegate.getValue(parameterName, Timestamp::valueOf);
    }

    @Override
    public URL getURL(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, it -> {
            try {
                return new URL(it);
            } catch (MalformedURLException e) {
                throw new SQLException(e);
            }
        });
    }

    @Override
    public RowId getRowId(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("getRowId");
    }

    @Override
    public RowId getRowId(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("getRowId");
    }

    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {

    }

    @Override
    public void setNString(String parameterName, String value) throws SQLException {

    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {

    }

    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {

    }

    @Override
    public void setClob(String parameterName, Reader reader, long length) throws SQLException {

    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {

    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {

    }

    @Override
    public NClob getNClob(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("getNClob");
    }

    @Override
    public NClob getNClob(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("getNClob");
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {

    }

    @Override
    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("getSQLXML");
    }

    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("getSQLXML");
    }

    @Override
    public String getNString(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, ThrowableFunction.identity());
    }

    @Override
    public String getNString(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, ThrowableFunction.identity());
    }

    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, StringReader::new);
    }

    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, StringReader::new);
    }

    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return delegate.getValue(parameterIndex, StringReader::new);
    }

    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {
        return delegate.getValue(parameterName, StringReader::new);
    }

    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {

    }

    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {

    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {

    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {

    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {

    }

    @Override
    public void setClob(String parameterName, Reader reader) throws SQLException {

    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {

    }

    @Override
    public void setNClob(String parameterName, Reader reader) throws SQLException {

    }

    @Override
    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
        Object o;
        if (type.isAssignableFrom(String.class)) {
            o = getString(parameterIndex);
        } else if (type.isAssignableFrom(Integer.class)) {
            o = getInt(parameterIndex);
        } else if (type.isAssignableFrom(Boolean.class)) {
            o = getBoolean(parameterIndex);
        } else if (type.isAssignableFrom(Double.class)) {
            o = getDouble(parameterIndex);
        } else {
            throw new IllegalArgumentException("Bad type.");
        }
        return type.cast(o);
    }

    @Override
    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
        Object o;
        if (type.isAssignableFrom(String.class)) {
            o = getString(parameterName);
        } else if (type.isAssignableFrom(Integer.class)) {
            o = getInt(parameterName);
        } else if (type.isAssignableFrom(Boolean.class)) {
            o = getBoolean(parameterName);
        } else if (type.isAssignableFrom(Double.class)) {
            o = getDouble(parameterName);
        } else {
            throw new IllegalArgumentException("Bad type.");
        }
        return type.cast(o);
    }

    @Override
    public java.sql.ResultSet executeQuery() throws SQLException {
        doBeforeExecute();
        return resultSet;
    }

    @Override
    public int executeUpdate() throws SQLException {
        doBeforeExecute();
        return 0;
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {

    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {

    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {

    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {

    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {

    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {

    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {

    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {

    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {

    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {

    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {

    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {

    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {

    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {

    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {

    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {

    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {

    }

    @Override
    public void clearParameters() throws SQLException {

    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {

    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {

    }

    @Override
    public boolean execute() throws SQLException {
        doBeforeExecute();
        return true;
    }

    @Override
    public void addBatch() throws SQLException {

    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {

    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {

    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {

    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {

    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {

    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {

    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {

    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {

    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {

    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {

    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {

    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {

    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {

    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {

    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {

    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {

    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {

    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {

    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {

    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {

    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {

    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {

    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {

    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {

    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {

    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {

    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {

    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {

    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {

    }

    @Override
    public java.sql.ResultSet executeQuery(String sql) throws SQLException {
        doBeforeExecute();
        return resultSet;
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        doBeforeExecute();
        return 0;
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return 0;
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {

    }

    @Override
    public int getMaxRows() throws SQLException {
        return 0;
    }

    @Override
    public void setMaxRows(int max) throws SQLException {

    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {

    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return 0;
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {

    }

    @Override
    public void cancel() throws SQLException {

    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public void clearWarnings() throws SQLException {

    }

    @Override
    public void setCursorName(String name) throws SQLException {

    }

    @Override
    public boolean execute(String sql) throws SQLException {
        doBeforeExecute();
        return true;
    }

    @Override
    public java.sql.ResultSet getResultSet() throws SQLException {
        return resultSet;
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return -1;
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return false;
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {

    }

    @Override
    public int getFetchDirection() throws SQLException {
        return 0;
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {

    }

    @Override
    public int getFetchSize() throws SQLException {
        return 0;
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return 0;
    }

    @Override
    public int getResultSetType() throws SQLException {
        return 0;
    }

    @Override
    public void addBatch(String sql) throws SQLException {

    }

    @Override
    public void clearBatch() throws SQLException {

    }

    @Override
    public int[] executeBatch() throws SQLException {
        doBeforeExecute();
        return new int[]{};
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return false;
    }

    @Override
    public java.sql.ResultSet getGeneratedKeys() throws SQLException {
        return resultSet;
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        doBeforeExecute();
        return 0;
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        doBeforeExecute();
        return 0;
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        doBeforeExecute();
        return 0;
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        doBeforeExecute();
        return true;
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        doBeforeExecute();
        return true;
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        doBeforeExecute();
        return true;
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return 0;
    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {

    }

    @Override
    public boolean isPoolable() throws SQLException {
        return false;
    }

    @Override
    public void closeOnCompletion() throws SQLException {

    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
