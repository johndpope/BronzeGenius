package Guice;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class HelloWorldModule extends AbstractModule {



    public static final String OWNER = "Lyroe Chan";

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(FireRocket.class), new FireRocketProcessor());
        //bind(String.class).annotatedWith(Names.named("private owner")).toInstance("Lyroe Chan");
        bind(Double.class).annotatedWith(Names.named("private amount")).toInstance(100.0);
        bind(String.class).annotatedWith(Names.named("Master owner")).toInstance("master owner");
        bind(Double.class).annotatedWith(Names.named("Master amount")).toInstance(10000.0);
        bind(Bill.class).to(AmazonBill.class);
        bind(Bill.class).annotatedWith(Paypal.class).to(PaypalBill.class);
        //bind(Bill.class).annotatedWith(Master.class).toProvider(BillProvider.class);
        try {
            bind(Transaction.class).toConstructor(Transaction.class.getConstructor(Bill.class));
        } catch (NoSuchMethodException e) {
            addError(e);
        }

        // cannot bind one source class to multiple target classes
        /*
        bind(SingletonManager.class).to(SellerManager.class);
        bind(SingletonManager.class).to(FinancialManager.class);
        */

        // can bind multiple source classes to one target class
        //bind(Financial.class).to(FinancialManager.class);
        //bind(SingletonManager.class).to(FinancialManager.class);

        //bind(Singl).toPro
        //bindInterceptor(Matchers.any(), Matchers.annotatedWith(FireRocket.class), new FireRocketProcessor());

    }

    @Provides
    private Person providePerson() {
        Person person = new Person();
        person.setAge(18);
        person.setName("Default Name");
        return person;
    }

    @Provides
    @Named("private owner")
    String provideOwner() {
        return OWNER;
    }

    @Provides
    @Named("COCK")
    String getProduct() {
        return "COKE COLA";
    }

    /**
     * @Provides will overwrite the @Inject, that is, @Provides will take effect when @Provides and @Inject are both existing
     * @Provides & @Singleton annotated on a provide method take the same effect as @Singleton annotated on a class and @Inject annotated on the Constrcutor.
     * @return
     */
    @Provides @Singleton
    private FinancialManager provieFinancialManager() {
        return new FinancialManager();
    }

}
