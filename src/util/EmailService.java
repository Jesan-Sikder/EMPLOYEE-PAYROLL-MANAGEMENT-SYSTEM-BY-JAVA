package util;

import model.Payroll;

/**
 * Simulates sending email notifications for payslips.
 */
public class EmailService {
    public static void sendPayslip(String toEmail, Payroll payroll) {
        // Simulate email by printing to console
        System.out.println("Sending payslip to " + toEmail + ":");
        System.out.println("Employee: " + payroll.getEmployeeName() +
                " | Net Salary: " + payroll.getNetSalary() +
                " | Month: " + payroll.getDate().getMonth() + " " + payroll.getDate().getYear());
        System.out.println("(Email simulated)\n");
    }
}