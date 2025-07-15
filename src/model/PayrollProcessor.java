package model;

import java.time.LocalDate;

/**
 * PayrollProcessor handles payroll calculation logic.
 */
public class PayrollProcessor {

    private TaxRule taxRule;

    public PayrollProcessor(TaxRule taxRule) {
        this.taxRule = taxRule;
    }

    /**
     * Process payroll for an employee for the current month.
     * @param employee Employee to process payroll for
     * @return Payroll record
     */
    public Payroll processPayroll(Employee employee) {
        double baseSalary = employee.getBaseSalary();
        double pfDeduction = baseSalary * employee.getPfPercent() / 100.0;
        double taxDeduction = baseSalary * employee.getTaxPercent() / 100.0;
        double bonus = employee.getBonus();
        double overtimePay = employee.getOvertimeHours() * taxRule.getOvertimeRatePerHour();
        double netSalary = baseSalary - pfDeduction - taxDeduction + bonus + overtimePay;

        Payroll payroll = new Payroll(
                employee.getId(),
                employee.getName(),
                LocalDate.now(),
                baseSalary,
                pfDeduction,
                taxDeduction,
                bonus,
                overtimePay,
                netSalary
        );
        employee.addPayroll(payroll);
        return payroll;
    }
}