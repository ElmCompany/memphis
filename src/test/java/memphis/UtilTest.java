package memphis;

import org.junit.Test;

import static memphis.Util.extractBaseFileSystem;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UtilTest {

    @Test
    public void testExtractProcName1() {
        assertThat(Util.extractProcName("{call test(?,?,?)}"), is("test"));
    }

    @Test
    public void testExtractProcName2() {
        assertThat(Util.extractProcName("{call test(?)}"), is("test"));
    }

    @Test
    public void testExtractProcName3() {
        assertThat(Util.extractProcName("{call test}"), is("test"));
    }

    @Test
    public void testExtractProcName4() {
        assertThat(Util.extractProcName("{call test (?)}"), is("test"));
    }

    @Test
    public void testExtractProcName5() {
        assertThat(Util.extractProcName("{call test }"), is("test"));
    }

    @Test
    public void testExtractProcName6() {
        assertThat(Util.extractProcName("{?=call test(?,?,?)}"), is("test"));
    }

    @Test
    public void testExtractProcName7() {
        assertThat(Util.extractProcName("{ ? = call test (?) }"), is("test"));
    }

    @Test
    public void testExtractBaseFileSystem() {
        assertThat(extractBaseFileSystem("jdbc:memphis:csv:file:///home/mhewedy/mocks"),
                is("/home/mhewedy/mocks"));
    }

    @Test
    public void testApply() {
        String value = Util.apply(" hello ", String::toUpperCase, String::trim);
        assertThat(value, is(equalTo("HELLO")));
    }
}