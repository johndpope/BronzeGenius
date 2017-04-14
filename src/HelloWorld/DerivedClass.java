package HelloWorld;

/**
 * @author xuch.
 */
public class DerivedClass  extends AbstractClass {
    public DerivedClass() {
        super("1");
    }

    public static void main(String args[]) {
        DerivedClass derivedClass = new DerivedClass();
        System.exit(0);
    }
}
