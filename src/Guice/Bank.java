package Guice;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author xuch.
 */
public class Bank {
    //private final Provider<SingletonManager> managerProvider;
    private final Provider<Bill> billProvider;

    @Inject
    public Bank(Provider<Bill> billProvider) {
        //this.managerProvider= managerProvider;
        this.billProvider = billProvider;
    }

    public void audit() {
        //SingletonManager manager = managerProvider.get();
        Bill bill = billProvider.get();
        System.out.println("Manager is auditing " + bill.toString());
    }
}
