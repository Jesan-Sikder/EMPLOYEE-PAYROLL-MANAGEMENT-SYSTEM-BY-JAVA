// package controller;

// import java.util.Map;
// import model.Employee;

// /**
//  * Handles CRUD operations for employees.
//  */
// public class EmployeeController {
//     private Map<String, Employee> employees;

//     public EmployeeController(Map<String, Employee> employees) {
//         this.employees = employees;
//     }

//     public Employee getEmployee(String id) {
//         return employees.get(id);
//     }

//     public boolean addEmployee(Employee emp) {
//         if (employees.containsKey(emp.getId())) return false;
//         employees.put(emp.getId(), emp);
//         return true;
//     }

//     public boolean editEmployee(String id, String name, String position, double salary, double pf, double tax, double bonus, int overtime) {
//         Employee emp = employees.get(id);
//         if (emp == null) return false;
//         emp.setName(name);
//         emp.setPosition(position);
//         emp.setBaseSalary(salary);
//         emp.setPfPercent(pf);
//         emp.setTaxPercent(tax);
//         emp.setBonus(bonus);
//         emp.setOvertimeHours(overtime);
//         return true;
//     }

//     public Map<String, Employee> getAllEmployees() {
//         return employees;
//     }
    
// }
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Employee;
import util.EmployeeFileUtil;

public class EmployeeController {
    private Map<String, Employee> employees;

    public EmployeeController(Map<String, Employee> employees) {
        this.employees = employees;
    }

    public Employee getEmployee(String id) {
        return employees.get(id);
    }

    public boolean addEmployee(Employee employee) {
        if (employees.containsKey(employee.getId())) {
            return false;
        } else {
            employees.put(employee.getId(), employee);
            saveAllEmployeesToFile("employees.json");
            return true;
        }
    }

    public boolean editEmployee(String id, String name, String position, 
    double baseSalary, double pfPercent, double taxPercent, double bonus,
     int overtimeHours) {
        Employee emp = employees.get(id);
        if (emp == null) {
            return false;
        } else {
            emp.setName(name);
            emp.setPosition(position);
            emp.setBaseSalary(baseSalary);
            emp.setPfPercent(pfPercent);
            emp.setTaxPercent(taxPercent);
            emp.setBonus(bonus);
            // emp.setOvertimeHours(overtimeCount);
               emp.setOvertimeHours(overtimeHours);
            saveAllEmployeesToFile("employees.json");
            return true;
        }
    }

    public Map<String, Employee> getAllEmployees() {
        return this.employees;
    }

    // New method to save all employees to JSON
    public void saveAllEmployeesToFile(String filename) {
        try {
            List<Employee> employeeList = new ArrayList<>(employees.values());
            EmployeeFileUtil.saveEmployees(employeeList, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}