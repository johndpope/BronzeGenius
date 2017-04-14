package annotations;

import org.junit.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpRetryException;

/**
 * @author xuch.
 */
public class TestAnnotationParser {
    @Test
    public String orderName;

    public void parser(Class<?> clazz) {
        Field[] fields = clazz.getFields();
        if (fields[0].isAnnotationPresent(Test.class)) {
            Test test = fields[0].getAnnotation(Test.class);
            System.out.println("default index of Test: " + test.index());
        }

        Method[] methods = clazz.getMethods();
        int pass = 0, fail = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                Class expected = test.expected();
                Exception exception = null;
                try {
                    if (method == null) System.out.println("method is null");
                    method.invoke(null);
                } catch (Exception e) {
                    /**
                     * A NullPointerException will be thrown if no exception throwns from the method
                     */
                    if (!(e instanceof NullPointerException)) {
                        exception = e;
                    }
                }
                if (expected == Test.None.class) {
                    if (exception == null) {
                        Assert.assertTrue(true);
                    } else {
                        System.out.println("exception: " + exception.getClass());
                        Assert.fail();
                    }
                } else {
                    System.out.println("expected: " + expected);
                    if (exception == null || exception.getClass() != expected) {
                        System.out.println("exception: " + exception.getClass());
                        Assert.fail();
                    } else {
                        Assert.assertTrue(true);
                    }
                }
            }
        }
    }

    @Test//(expected = IllegalArgumentException.class)
    public void test1() throws Exception {
        System.out.println("test 1");
        throw new IllegalArgumentException();
    }

    @Test
    public void test2() {
        System.out.println("test 2");
        Assert.assertTrue(true);
    }

    public static void main(String args[]) {
        TestAnnotationParser parser = new TestAnnotationParser();

        parser.parser(TestAnnotationParser.class);
    }
}
