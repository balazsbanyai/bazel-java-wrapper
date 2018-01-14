import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.banyaibalazs.bazel.packag.PackageGuesser;
import com.banyaibalazs.bazel.packag.PropertyGetter;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by bbanyai on 14/01/18.
 */
public class PackageGuesserTest {


    PackageGuesser guesser;

    @Mock
    private PropertyGetter getter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        guesser = new PackageGuesser(getter);
    }

    @Test
    public void testWinOs_winPackageReturned() throws Exception {

        when(getter.getProperty(anyString())).thenReturn("Windows");

        String actual = guesser.guessPackage().version("0.9.0").build();

        String expected = "bazel-0.9.0-without-jdk-windows-x86_64.exe";
        assertEquals(expected, actual);
    }

    @Test
    public void testDarwinOs_darwinPackageReturned() throws Exception {

        when(getter.getProperty(anyString())).thenReturn("Mac OS X");

        String actual = guesser.guessPackage().version("0.9.0").build();

        String expected = "bazel-0.9.0-without-jdk-installer-darwin-x86_64.sh";
        assertEquals(expected, actual);
    }

    @Test
    public void testLinuxOs_linuxPackageReturned() throws Exception {

        when(getter.getProperty(anyString())).thenReturn("Linux");

        String actual = guesser.guessPackage().version("0.9.0").build();

        String expected = "bazel-0.9.0-without-jdk-installer-linux-x86_64.sh";
        assertEquals(expected, actual);
    }
}
