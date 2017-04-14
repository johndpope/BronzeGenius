package HelloWorld;

/**
 * @author xuch.
 */
public class PrivateConstructor {
    private String name;
    private boolean young;

    public PrivateConstructor(String name, boolean young) {
        this(name);
        this.young = young;
    }

    private PrivateConstructor(String name) {
        this.name = name;
    }
}
