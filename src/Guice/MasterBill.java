package Guice;

import lombok.Data;
import org.slf4j.helpers.MessageFormatter;

@Data
public class MasterBill extends Bill {
    private String owner;
    private double amount;

    @Override
    public void charge() {
        System.out.println(MessageFormatter.format("Charging {} from {}'s Master bill", amount, owner).getMessage());
    }

    @Override
    public String toString() {
        return MessageFormatter.format("Master bill owned by {} and amounted at {}", owner, amount).getMessage();
    }
}
