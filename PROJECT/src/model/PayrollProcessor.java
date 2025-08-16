package model;

import java.time.LocalDate;


public class PayrollProcessor {

    private TaxRule taxRule;

    public PayrollProcessor(TaxRule taxRule) {
        this.taxRule = taxRule;
    }

    
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