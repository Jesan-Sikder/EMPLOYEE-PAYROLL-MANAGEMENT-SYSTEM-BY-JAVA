package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Employee class that extends User and holds payroll information.
 */
public class Employee extends User {
    private String position;
    private double baseSalary;
    private double pfPercent;
    private double taxPercent;
    private double bonus;
    private int overtimeHours;
    private List<Payroll> payrollHistory;

    public Employee(String id, String password, String name, String email,
                    String position, double baseSalary, double pfPercent, double taxPercent,
                    double bonus, int overtimeHours) {
        super(id, password, name, email);
        this.position = position;
        this.baseSalary = baseSalary;
        this.pfPercent = pfPercent;
        this.taxPercent = taxPercent;
        this.bonus = bonus;
        this.overtimeHours = overtimeHours;
        this.payrollHistory = new ArrayList<>();
    }

    // Getters and setters
    public String getPosition() { return position; }
    public double getBaseSalary() { return baseSalary; }
    public double getPfPercent() { return pfPercent; }
    public double getTaxPercent() { return taxPercent; }
    public double getBonus() { return bonus; }
    public int getOvertimeHours() { return overtimeHours; }
    public List<Payroll> getPayrollHistory() { return payrollHistory; }

    public void setPosition(String position) { this.position = position; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }
    public void setPfPercent(double pfPercent) { this.pfPercent = pfPercent; }
    public void setTaxPercent(double taxPercent) { this.taxPercent = taxPercent; }
    public void setBonus(double bonus) { this.bonus = bonus; }
    public void setOvertimeHours(int overtimeHours) { this.overtimeHours = overtimeHours; }

    public void addPayroll(Payroll payroll) { this.payrollHistory.add(payroll); }
}