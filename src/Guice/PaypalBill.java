package Guice;

import com.google.inject.Inject;
import org.slf4j.helpers.MessageFormatter;

@Paypal
public class PaypalBill extends Bill {
    private Person owner;

    @Inject
    public PaypalBill(Person owner) {
        this.owner = owner;
    }

    @Override
    public void charge() {
        System.out.println(MessageFormatter.format("Charging Paypal bill from {}", owner.toString()).getMessage());
    }

    @Override
    public String toString() {
        return MessageFormatter.format("Paypal bill owned by {}", owner.toString()).getMessage();
    }
}
