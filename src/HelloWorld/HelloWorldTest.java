package HelloWorld;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * @author xuch.
 */
public class HelloWorldTest {
    @Test(expected = IllegalArgumentException.class)
    public void happyCase() {
        Assert.assertTrue(true);
    }
}
