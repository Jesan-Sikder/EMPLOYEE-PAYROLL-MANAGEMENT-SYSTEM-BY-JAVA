
package model;

import java.time.LocalDate;

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

    public Payroll() {
     
    }

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

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }

    public double getPfDeduction() { return pfDeduction; }
    public void setPfDeduction(double pfDeduction) { this.pfDeduction = pfDeduction; }

    public double getTaxDeduction() { return taxDeduction; }
    public void setTaxDeduction(double taxDeduction) { this.taxDeduction = taxDeduction; }

    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    public double getOvertimePay() { return overtimePay; }
    public void setOvertimePay(double overtimePay) { this.overtimePay = overtimePay; }

    public double getNetSalary() { return netSalary; }
    public void setNetSalary(double netSalary) { this.netSalary = netSalary; }
}