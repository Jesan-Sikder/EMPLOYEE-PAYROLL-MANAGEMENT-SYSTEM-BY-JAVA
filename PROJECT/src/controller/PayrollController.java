package controller;

import model.Employee;
import model.Payroll;
import model.PayrollProcessor;
import util.EmailService;

import java.util.Map;


public class PayrollController {
    private PayrollProcessor payrollProcessor;
    private Map<String, Employee> employees;

    public PayrollController(PayrollProcessor payrollProcessor, Map<String, Employee> employees) {
        this.payrollProcessor = payrollProcessor;
        this.employees = employees;
    }

    public Payroll processPayrollForEmployee(String id) {
        Employee emp = employees.get(id);
        if (emp == null) return null;
        Payroll payroll = payrollProcessor.processPayroll(emp);
        EmailService.sendPayslip(emp.getEmail(), payroll);
        return payroll;
    }

    public void processPayrollForAll() {
        for (Employee emp : employees.values()) {
            Payroll payroll = payrollProcessor.processPayroll(emp);
            EmailService.sendPayslip(emp.getEmail(), payroll);
        }
    }
}