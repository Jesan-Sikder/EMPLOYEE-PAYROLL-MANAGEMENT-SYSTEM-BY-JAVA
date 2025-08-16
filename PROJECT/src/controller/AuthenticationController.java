package controller;

import model.Admin;
import model.Employee;
import java.util.Map;


public class AuthenticationController {
    private Admin admin;
    private Map<String, Employee> employees;

    public AuthenticationController(Admin admin, Map<String, Employee> employees) {
        this.admin = admin;
        this.employees = employees;
    }

    public boolean authenticateAdmin(String username, String password) {
        return admin.getId().equals(username) && admin.getPassword().equals(password);
    }

    public Employee authenticateEmployee(String id, String password) {
        Employee emp = employees.get(id);
        if (emp != null && emp.getPassword().equals(password)) {
            return emp;
        }
        return null;
    }
}