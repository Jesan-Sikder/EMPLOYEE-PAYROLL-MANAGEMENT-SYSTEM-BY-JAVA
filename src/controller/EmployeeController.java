package controller;

import model.Employee;

import java.util.Map;

/**
 * Handles CRUD operations for employees.
 */
public class EmployeeController {
    private Map<String, Employee> employees;

    public EmployeeController(Map<String, Employee> employees) {
        this.employees = employees;
    }

    public Employee getEmployee(String id) {
        return employees.get(id);
    }

    public boolean addEmployee(Employee emp) {
        if (employees.containsKey(emp.getId())) return false;
        employees.put(emp.getId(), emp);
        return true;
    }

    public boolean editEmployee(String id, String name, String position, double salary, double pf, double tax, double bonus, int overtime) {
        Employee emp = employees.get(id);
        if (emp == null) return false;
        emp.setName(name);
        emp.setPosition(position);
        emp.setBaseSalary(salary);
        emp.setPfPercent(pf);
        emp.setTaxPercent(tax);
        emp.setBonus(bonus);
        emp.setOvertimeHours(overtime);
        return true;
    }

    public Map<String, Employee> getAllEmployees() {
        return employees;
    }
}