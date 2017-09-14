package Guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

/**
 * @author xuch.
 */
public class BillProvider implements Provider<Bill> {
    private String owner;
    private double amount;

    @Inject
    public BillProvider(@Named("Master owner") String owner, @Named("Master amount") double amount) {
        this.owner = owner;
        this.amount = amount;
    }

    @Override
    public Bill get() {
        MasterBill masterBill = new MasterBill();
        masterBill.setAmount(amount);
        masterBill.setOwner(owner);
        return masterBill;
    }
}
