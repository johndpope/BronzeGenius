package Guice;

import com.google.inject.Inject;

/**
 * @author xuch.
 */
public class AbstractClass {
    @Inject
    protected AmazonBill amazonBill;

    protected void save() {
        amazonBill.charge();
    }
}
