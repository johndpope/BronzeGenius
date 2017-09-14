package Guice;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.junit.Test;

import java.util.Date;

/**
 * @author xuch.
 */
@Singleton
public class Utility {

    @Test
    public void test() {
        System.out.println(new Date().getTime());
    }
}
