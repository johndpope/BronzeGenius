package geom.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;

/**
 * Base class for test cases, loads the unit testing spring configuration
 * context and does some other setup before execution, then cleans up
 * afterwards. It also contains a few helper methods for creating unit tests.
 */
public abstract class AbstractCaseTest {

    protected double delta;

    // set up the initialization before all tests.
    @Before
    public void setUp() throws Throwable {
        // TODO
        delta = 1e-15;
    }
}
