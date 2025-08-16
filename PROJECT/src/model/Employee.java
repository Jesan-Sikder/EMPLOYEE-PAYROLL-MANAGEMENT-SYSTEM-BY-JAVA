

package model;
import java.util.ArrayList;
import java.util.List;

public class Employee extends User {
    private String position;
    private double baseSalary;
    private double pfPercent;
    private double taxPercent;
    private double bonus;
    private int overtimeHours;
    private List<Payroll> payrollHistory;

    public Employee() {
        super();
        this.payrollHistory = new ArrayList<>();
    }

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

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }

    public double getPfPercent() { return pfPercent; }
    public void setPfPercent(double pfPercent) { this.pfPercent = pfPercent; }

    public double getTaxPercent() { return taxPercent; }
    public void setTaxPercent(double taxPercent) { this.taxPercent = taxPercent; }

    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    public int getOvertimeHours() { return overtimeHours; }
    public void setOvertimeHours(int overtimeHours) { this.overtimeHours = overtimeHours; }

    public List<Payroll> getPayrollHistory() { return payrollHistory; }
    public void setPayrollHistory(List<Payroll> payrollHistory) { this.payrollHistory = payrollHistory; }

    public void addPayroll(Payroll payroll) { this.payrollHistory.add(payroll); }
}