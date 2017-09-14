package Guice;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

@Slf4j
public class AmazonBill extends Bill {
    private String owner;
    private Double amount;

    @Inject
    public AmazonBill(@Named("private owner") String owner, @Named("private amount") Double amount) {
        this.owner = owner;
        this.amount = amount;
    }

    @Override
    public void charge() {
        System.out.println(MessageFormatter.format("Charging {} from {}'s Amazon bill", amount, owner).getMessage());
        log.info("");
    }

    @Override
    public String toString() {
        return MessageFormatter.format("Amazon bill owned by {} and amounted at {}", owner, amount).getMessage();
    }
}
