package annotations;

/**
 * @author xuch.
 */
public class Main {
    public static void main(String args[]) {
        TestAnnotationParser parser = new TestAnnotationParser();

        parser.parser(TestAnnotationParser.class);
    }
}
