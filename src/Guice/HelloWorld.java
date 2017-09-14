package Guice;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class HelloWorld {
    @Inject
    @Named("COCK")
    private String product;

    @Inject
    private Utility utility;
    /**
     * Injection Points:
     * Constructor injected, (@Inject-annotated construtor may be at most only one and must not be optional)
     * Fields injected then,
     * Methods injected finally.
     *
     *
     * https://github.com/google/guice/wiki/InjectionPoints
     */
    //@Inject
    private void setPrivateFinancialManager() {
        System.out.println("Set the PrivateFinancialManager to null");
        this.privateFinancialManager = null;
    }

    @Inject
    private FinancialManager privateFinancialManager;

    public static void main(String[] args){
        Injector injector = Guice.createInjector(new HelloWorldModule(), new StorageModule());
        HelloWorld helloWorld = injector.getInstance(HelloWorld.class);
        Bill bill = injector.getInstance(Bill.class);
        bill.charge();
        Bill amazonBill = injector.getInstance(AmazonBill.class);
        amazonBill.charge();
        Bill paypalBill = injector.getInstance(PaypalBill.class);
        paypalBill.charge();

        BillProvider billProvider = injector.getInstance(BillProvider.class);
        Bill masterBill = billProvider.get();
        masterBill.charge();

        Transaction transaction = injector.getInstance(Transaction.class);
        System.out.println(transaction.toString());
        transaction.getBill().charge();


        //injector.getInstance(Financial.class);
        //injector.getInstance(SingletonManager.class);
        injector.getInstance(FinancialManager.class);System.out.println("-");

        injector.getInstance(FinancialDepartment.class);
        injector.getInstance(FinancialDepartment.class);

        //injector.getInstance(Financial.class);
        //injector.getInstance(SingletonManager.class);
        injector.getInstance(FinancialManager.class);

        //Bank bank = injector.getInstance(Bank.class);
        //bank.audit();

        helloWorld.fireRocket();

        System.out.println("product:" + helloWorld.product);

        HelloWorld helloWorld1 = new HelloWorld();
        System.out.println("product:" + helloWorld1.product);
        injector.injectMembers(helloWorld1);
        System.out.println("product:" + helloWorld1.product);

        //Utility utility = injector.getInstance(Utility.class);
        System.out.println(helloWorld.utility != null ? "utility is not null" : "utility is null");
        System.out.println(helloWorld1.utility != null ? "utility is not null" : "utility is null");
    }

    @FireRocket("NASA")
    private void fireRocket() {
        privateFinancialManager.callOut();
        System.out.println("Begin to Fire Rocket!");
    }
}
