package HelloWorld;

/**
 * @author xuch.
 */
public class AbstractClass {
    private String id;

    /**
     * constructor in abstract class will be default invoked without arguments in a derived class constructor if no explicit invocation.
     * So if a constructor in abstract class has arguments, then a explicit invocation should be specified.
     * @param id
     */
    public AbstractClass(String id) {
        this.id = id;
    }
}
