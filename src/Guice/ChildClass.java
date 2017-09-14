package Guice;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.name.Named;

/**
 * @author xuch.
 */
public class ChildClass extends AbstractClass {
    @Inject
    @Named("child")
    private AmazonBill bill;

}
