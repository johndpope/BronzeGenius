package HelloWorld;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

/**
 * @author xuch.
 */
public class HelloWorldTest {
    @Test(expected = IllegalArgumentException.class)
    public void happyCase() {
        Assert.assertTrue(true);
    }

    @Test
    public void testListStream() {
        List<String> stringList = new ArrayList<String>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("v");
        stringList.add("g");

        List<String> result = stringList.stream().map(String::toUpperCase).collect(Collectors.toList());
        result.forEach(System.out::println);
    }
}
