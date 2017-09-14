package Guice;

import com.google.inject.Inject;

/**
 * @author xuch.
 */
public class FinancialDepartment {
    private static int index = 0;
    private FinancialManager financialManager;

    @Inject
    public FinancialDepartment(FinancialManager financialManager) {
        this.financialManager = financialManager;
        System.out.println("Creating an instance of FinancialDepartment: " + index++);
    }
}
