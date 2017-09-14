package Guice;

import lombok.Getter;

/**
 * @author xuch.
 */
public class Transaction {
    @Getter
    private Bill bill;

    public Transaction(Bill bill) {
        this.bill = bill;
    }

    public String toString() {
        return "Transaction of " + bill.toString();
    }
}
