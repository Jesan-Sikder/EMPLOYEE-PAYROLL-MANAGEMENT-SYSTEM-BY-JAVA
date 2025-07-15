package model;

import java.time.LocalDate;

/**
 * Represents a monthly payroll record for an employee.
 */
public class Payroll {
    private String employeeId;
    private String employeeName;
    private LocalDate date;
    private double baseSalary;
    private double pfDeduction;
    private double taxDeduction;
    private double bonus;
    private double overtimePay;
    private double netSalary;

    public Payroll(String employeeId, String employeeName, LocalDate date, double baseSalary,
                   double pfDeduction, double taxDeduction, double bonus, double overtimePay, double netSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.baseSalary = baseSalary;
        this.pfDeduction = pfDeduction;
        this.taxDeduction = taxDeduction;
        this.bonus = bonus;
        this.overtimePay = overtimePay;
        this.netSalary = netSalary;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public LocalDate getDate() { return date; }
    public double getBaseSalary() { return baseSalary; }
    public double getPfDeduction() { return pfDeduction; }
    public double getTaxDeduction() { return taxDeduction; }
    public double getBonus() { return bonus; }
    public double getOvertimePay() { return overtimePay; }
    public double getNetSalary() { return netSalary; }
}