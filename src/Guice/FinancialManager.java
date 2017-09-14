package Guice;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author xuch.
 */

/**
 * annotated with scope Singleton, constructor will only be invoked one time, that means during the application life cycle, there is only one instance of FinancialManager
 * annotated without scope Singleton, constructor will be invoked each time the injector.getInstance() is invoked, that is, there will be multiple instances in the same number as the injector is invoked
 */
@Singleton
public class FinancialManager implements SingletonManager, Financial {
    private static int index = 0;

    /**
     * @Inject-annotated method will executed when injector.getInstance() is invoked
     */
    @Inject
    public void callOut() {
        System.out.println("FinancialManager calls out!");
    }
    /**
     * If your class has no @Inject-annotated constructor, Guice will use a public, no-arguments constructor if it exists.
     */
    //@Inject
    public FinancialManager() {
        System.out.println("Creating an instance of FinancialManager: " + index++);
    }


}
