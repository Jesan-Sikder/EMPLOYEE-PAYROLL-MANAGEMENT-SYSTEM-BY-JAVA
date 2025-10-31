package util;

import java.util.List;
import java.util.Map;
import model.Employee;
import model.Payroll;

public class ReportGenerator {
    public static String generatePayrollReport(Map<String, Employee> employees) {
        StringBuilder sb = new StringBuilder();
        sb.append("Payroll Report\n");
        sb.append("ID\tName\tPosition\tNet Salary (Last Month)\n");
        for (Employee emp : employees.values()) {
            List<Payroll> history = emp.getPayrollHistory();
            Payroll last = history.isEmpty() ? null : history.get(history.size()-1);
            double net = (last != null) ? last.getNetSalary() : 0.0;
            sb.append(emp.getId()).append("\t")
              .append(emp.getName()).append("\t")
              .append(emp.getPosition()).append("\t")
              .append(String.format("%.2f", net)).append("\n");
        }
        return sb.toString();
    }

    public static String generateTaxReport(Map<String, Employee> employees) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tax Report\n");
        sb.append("ID\tName\tTax Deducted (Last Month)\n");
        for (Employee emp : employees.values()) {
            List<Payroll> history = emp.getPayrollHistory();
            Payroll last = history.isEmpty() ? null : history.get(history.size()-1);
            double tax = (last != null) ? last.getTaxDeduction() : 0.0;
            sb.append(emp.getId()).append("\t")
              .append(emp.getName()).append("\t")
              .append(String.format("%.2f", tax)).append("\n");
        }
        return sb.toString();
    }

 
    public static Object[][] generatePayrollTable(Map<String, Employee> employees) {
        Object[][] data = new Object[employees.size()][5]; // Adjust column count if needed
        int i = 0;
        for (Employee emp : employees.values()) {
            List<Payroll> history = emp.getPayrollHistory();
            Payroll last = history.isEmpty() ? null : history.get(history.size()-1);
            double net = (last != null) ? last.getNetSalary() : 0.0;
            double bonus = (last != null) ? last.getBonus() : 0.0;
            data[i][0] = emp.getId();
            data[i][1] = emp.getName();
            data[i][2] = emp.getPosition();
            data[i][3] = String.format("%.2f", net);
            data[i][4] = String.format("%.2f", bonus);
            i++;
        }
        return data;
    }
}