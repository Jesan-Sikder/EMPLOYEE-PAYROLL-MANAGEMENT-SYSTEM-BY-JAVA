// import model.*;
// import util.*;
// import controller.*;
// import view.*;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.util.HashMap;
// import java.util.Map;

// /**
//  * Main class to launch the Employee Payroll Management System.
//  */
// public class Main {
//     private static JFrame frame;
//     private static CardLayout cardLayout;
//     private static JPanel mainPanel;

//     // Data
//     private static Admin admin;
//     private static HashMap<String, Employee> employees;
//     private static TaxRule taxRule;
//     private static PayrollProcessor payrollProcessor;

//     // Controllers
//     private static AuthenticationController authController;
//     private static EmployeeController employeeController;
//     private static PayrollController payrollController;
//     private static TaxController taxController;

//     // Views
//     private static WelcomeScreen welcomeScreen;
//     private static LoginPanel adminLoginPanel, empLoginPanel;
//     private static AdminDashboard adminDashboard;
//     private static EmployeeDashboard employeeDashboard;
//     private static EmployeeManagementPanel empMgmtPanel;
//     private static PayrollHistoryPanel payrollHistoryPanel;
//     private static PayrollHistoryPanel empPayrollHistoryPanel;
//     private static ReportPanel reportPanel;
//     private static TaxRulePanel taxRulePanel;

//     // State
//     private static Employee currentEmployee;

//     public static void main(String[] args) {
//         // Data setup
//         admin = new Admin("hr545", "24115628", "HR Manager", "hr@company.com");
//         employees = DataGenerator.generateEmployees(201);

//         // Print all employee IDs and passwords for reference
//         for (Employee emp : employees.values()) {
//             System.out.println("ID: " + emp.getId() + " | Password: " + emp.getPassword());
//         }

//         taxRule = new TaxRule(8.0, 15.0, 1000.0, 200.0); // Default
//         payrollProcessor = new PayrollProcessor(taxRule);

//         // Controllers
//         authController = new AuthenticationController(admin, employees);
//         employeeController = new EmployeeController(employees);
//         payrollController = new PayrollController(payrollProcessor, employees);
//         taxController = new TaxController(taxRule);

//         // GUI
//         SwingUtilities.invokeLater(() -> {
//             frame = new JFrame("Employee Payroll Management System");
//             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//             frame.setSize(1200, 700);
//             frame.setLocationRelativeTo(null);

//             cardLayout = new CardLayout();
//             mainPanel = new JPanel(cardLayout);

//             // Welcome Screen
//             welcomeScreen = new WelcomeScreen(
//                 e -> showPanel("adminLogin"),
//                 e -> showPanel("empLogin")
//             );

//             // Admin Login Panel
//             adminLoginPanel = new LoginPanel("Admin Login", e -> {
//                 String id = adminLoginPanel.getId();
//                 String password = adminLoginPanel.getPassword();
//                 if (authController.authenticateAdmin(id, password)) {
//                     showPanel("adminDashboard");
//                 } else {
//                     adminLoginPanel.showError("Invalid admin credentials!");
//                 }
//             });

//             // Employee Login Panel
//             empLoginPanel = new LoginPanel("Employee Login", e -> {
//                 String id = empLoginPanel.getId();
//                 String password = empLoginPanel.getPassword();
//                 Employee emp = authController.authenticateEmployee(id, password);
//                 if (emp != null) {
//                     currentEmployee = emp;
//                     employeeDashboard = new EmployeeDashboard(emp.getName(), empListener);
//                     mainPanel.add(employeeDashboard, "employeeDashboard");
//                     showPanel("employeeDashboard");
//                 } else {
//                     empLoginPanel.showError("Invalid employee ID or password!");
//                 }
//             });

//             // Admin Dashboard
//             adminDashboard = new AdminDashboard(adminListener);

//             // Employee Management
//             empMgmtPanel = new EmployeeManagementPanel(adminListener);
//             empMgmtPanel.loadEmployees(employees);

//             // Payroll History Panel
//             payrollHistoryPanel = new PayrollHistoryPanel("All Employees Payroll History");

//             // Tax Rule Panel
//             taxRulePanel = new TaxRulePanel(e -> {
//                 try {
//                     double pf = taxRulePanel.getPf();
//                     double taxPercent = taxRulePanel.getTax();
//                     double bonus = taxRulePanel.getBonus();
//                     double overtime = taxRulePanel.getOvertime();
//                     taxController.updateTaxRule(pf, taxPercent, bonus, overtime);
//                     JOptionPane.showMessageDialog(frame, "Tax rules updated successfully!");
//                 } catch (Exception ex) {
//                     JOptionPane.showMessageDialog(frame, "Invalid values!");
//                 }
//             }, adminListener);

//             // Add all panels to mainPanel
//             mainPanel.add(welcomeScreen, "welcome");
//             mainPanel.add(adminLoginPanel, "adminLogin");
//             mainPanel.add(empLoginPanel, "empLogin");
//             mainPanel.add(adminDashboard, "adminDashboard");
//             mainPanel.add(empMgmtPanel, "empMgmt");
//             mainPanel.add(payrollHistoryPanel, "payrollHistory");
//             mainPanel.add(taxRulePanel, "taxRules");

//             frame.setContentPane(mainPanel);
//             showPanel("welcome");
//             frame.setVisible(true);
//         });
//     }

//     // Helper to switch panels
//     private static void showPanel(String name) {
//         cardLayout.show(mainPanel, name);
//     }

//     // --- Action Listeners ---

//     // Admin dashboard navigation
//     private static ActionListener adminListener = new ActionListener() {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             Object src = e.getSource();
//             if (src == adminDashboard.btnManageEmp) {
//                 empMgmtPanel.loadEmployees(employees);
//                 showPanel("empMgmt");
//             } else if (src == adminDashboard.btnPayrollHistory) {
//                 payrollHistoryPanel = new PayrollHistoryPanel("All Employees Payroll History");
//                 payrollHistoryPanel.loadAllPayrolls(employees.values());
//                 payrollHistoryPanel.btnBack.addActionListener(adminListener);
//                 mainPanel.add(payrollHistoryPanel, "payrollHistory");
//                 showPanel("payrollHistory");
//             } else if (src == adminDashboard.btnReports) {
//                 String[] columnNames = {"ID", "Name", "Position", "Salary", "Bonus"};
//                 Object[][] reportData = util.ReportGenerator.generatePayrollTable(employees);
//                 reportPanel = new ReportPanel("Payroll Report", reportData, columnNames, adminListener);
//                 mainPanel.add(reportPanel, "report");
//                 showPanel("report");
//             } else if (src == adminDashboard.btnTaxRules) {
//                 taxRulePanel.loadValues(
//                         taxRule.getDefaultPfPercent(),
//                         taxRule.getDefaultTaxPercent(),
//                         taxRule.getDefaultBonus(),
//                         taxRule.getOvertimeRatePerHour()
//                 );
//                 showPanel("taxRules");
//             } else if (src == adminDashboard.btnProcessPayroll) {
//                 payrollController.processPayrollForAll();
//                 JOptionPane.showMessageDialog(frame, "Payroll processed and payslips sent for all employees.");
//             } else if (src == adminDashboard.btnLogout) {
//                 showPanel("welcome");
//             } else if (src == empMgmtPanel.btnBack) {
//                 showPanel("adminDashboard");
//             } else if (src == empMgmtPanel.btnAdd) {
//                 // Add new employee dialog
//                 Employee emp = getEmployeeFromDialog(null);
//                 if (emp != null) {
//                     if (employeeController.addEmployee(emp)) {
//                         JOptionPane.showMessageDialog(frame, "Employee added.");
//                         empMgmtPanel.loadEmployees(employees);
//                     } else {
//                         JOptionPane.showMessageDialog(frame, "Employee ID already exists!");
//                     }
//                 }
//             } else if (src == empMgmtPanel.btnEdit) {
//                 int row = empMgmtPanel.table.getSelectedRow();
//                 if (row == -1) {
//                     JOptionPane.showMessageDialog(frame, "Select an employee to edit.");
//                     return;
//                 }
//                 String id = (String) empMgmtPanel.tableModel.getValueAt(row, 0);
//                 Employee emp = employees.get(id);
//                 Employee updated = getEmployeeFromDialog(emp);
//                 if (updated != null) {
//                     employeeController.editEmployee(id,
//                             updated.getName(), updated.getPosition(), updated.getBaseSalary(),
//                             updated.getPfPercent(), updated.getTaxPercent(), updated.getBonus(),
//                             updated.getOvertimeHours());
//                     JOptionPane.showMessageDialog(frame, "Employee updated.");
//                     empMgmtPanel.loadEmployees(employees);
//                 }
//             } else if (src == payrollHistoryPanel.btnBack) {
//                 showPanel("adminDashboard");
//             } else if (src == taxRulePanel.btnBack) {
//                 showPanel("adminDashboard");
//             } else if (reportPanel != null && src == reportPanel.btnBack) {
//                 showPanel("adminDashboard");
//             }
//         }
//     };

//     // Employee dashboard navigation
//     private static ActionListener empListener = new ActionListener() {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             Object src = e.getSource();
//             if (src == employeeDashboard.btnViewPayslip) {
//                 Payroll payroll = payrollController.processPayrollForEmployee(currentEmployee.getId());
//                 String msg = "Payslip for " + payroll.getDate().getMonth() + " " + payroll.getDate().getYear() +
//                         "\nNet Salary: " + payroll.getNetSalary();
//                 JOptionPane.showMessageDialog(frame, msg);
//             } else if (src == employeeDashboard.btnPayrollHistory) {
//                 empPayrollHistoryPanel = new PayrollHistoryPanel("Your Payroll History");
//                 empPayrollHistoryPanel.loadPayrollHistory(
//                     currentEmployee.getPayrollHistory(),
//                     currentEmployee.getId(),
//                     currentEmployee.getName()
//                 );
//                 empPayrollHistoryPanel.btnBack.addActionListener(empListener);
//                 mainPanel.add(empPayrollHistoryPanel, "empPayrollHistory");
//                 showPanel("empPayrollHistory");
//             } else if (src == employeeDashboard.btnLogout) {
//                 showPanel("welcome");
//             } else if (empPayrollHistoryPanel != null && src == empPayrollHistoryPanel.btnBack) {
//                 showPanel("employeeDashboard");
//             }
//         }
//     };

//     // Modern dialog for adding/editing employee
//     private static Employee getEmployeeFromDialog(Employee emp) {
//         ModernEmployeeDialog dialog = new ModernEmployeeDialog(frame, emp, emp != null);
//         dialog.setVisible(true);
//         if (dialog.isConfirmed()) {
//             Employee result = dialog.getEmployee();
//             if (result == null) {
//                 JOptionPane.showMessageDialog(frame, "Invalid input!");
//             }
//             return result;
//         }
//         return null;
//     }
// }
import model.*;
import util.*;
import controller.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class to launch the Employee Payroll Management System.
 */
public class Main {
    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;

    // Data
    private static Admin admin;
    private static HashMap<String, Employee> employees;
    private static TaxRule taxRule;
    private static PayrollProcessor payrollProcessor;

    // Controllers
    private static AuthenticationController authController;
    private static EmployeeController employeeController;
    private static PayrollController payrollController;
    private static TaxController taxController;

    // Views
    private static WelcomeScreen welcomeScreen;
    private static LoginPanel adminLoginPanel, empLoginPanel;
    private static AdminDashboard adminDashboard;
    private static EmployeeDashboard employeeDashboard;
    private static EmployeeManagementPanel empMgmtPanel;
    private static PayrollHistoryPanel payrollHistoryPanel;
    private static PayrollHistoryPanel empPayrollHistoryPanel;
    private static ReportPanel reportPanel;
    private static TaxRulePanel taxRulePanel;

    // State
    private static Employee currentEmployee;

    // Persistence file
    private static final String EMPLOYEE_FILE = "employees.json";

    public static void main(String[] args) {
        // Data setup
        admin = new Admin("hr545", "24115628", "HR Manager", "hr@company.com");

        // Try to load employees from file, else generate new
        try {
            employees = new HashMap<>();
            List<Employee> loadedEmployees = EmployeeFileUtil.loadEmployees(EMPLOYEE_FILE);
            for (Employee emp : loadedEmployees) {
                employees.put(emp.getId(), emp);
            }
            System.out.println("Loaded employees from file.");
        } catch (Exception ex) {
            employees = DataGenerator.generateEmployees(201);
            System.out.println("Generated new employees.");
            // Also save to file for next run
            try {
                EmployeeFileUtil.saveEmployees(new ArrayList<>(employees.values()), EMPLOYEE_FILE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Print all employee IDs and passwords for reference
        for (Employee emp : employees.values()) {
            System.out.println("ID: " + emp.getId() + " | Password: " + emp.getPassword());
        }

        taxRule = new TaxRule(8.0, 15.0, 1000.0, 200.0); // Default
        payrollProcessor = new PayrollProcessor(taxRule);

        // Controllers
        authController = new AuthenticationController(admin, employees);
        employeeController = new EmployeeController(employees);
        payrollController = new PayrollController(payrollProcessor, employees);
        taxController = new TaxController(taxRule);

        // GUI
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Employee Payroll Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 700);
            frame.setLocationRelativeTo(null);

            cardLayout = new CardLayout();
            mainPanel = new JPanel(cardLayout);

            // Welcome Screen
            welcomeScreen = new WelcomeScreen(
                e -> showPanel("adminLogin"),
                e -> showPanel("empLogin")
            );

            // Admin Login Panel
            adminLoginPanel = new LoginPanel("Admin Login", e -> {
                String id = adminLoginPanel.getId();
                String password = adminLoginPanel.getPassword();
                if (authController.authenticateAdmin(id, password)) {
                    showPanel("adminDashboard");
                } else {
                    adminLoginPanel.showError("Invalid admin credentials!");
                }
            });

            // Employee Login Panel
            empLoginPanel = new LoginPanel("Employee Login", e -> {
                String id = empLoginPanel.getId();
                String password = empLoginPanel.getPassword();
                Employee emp = authController.authenticateEmployee(id, password);
                if (emp != null) {
                    currentEmployee = emp;
                    employeeDashboard = new EmployeeDashboard(emp.getName(), empListener);
                    mainPanel.add(employeeDashboard, "employeeDashboard");
                    showPanel("employeeDashboard");
                } else {
                    empLoginPanel.showError("Invalid employee ID or password!");
                }
            });

            // Admin Dashboard
            adminDashboard = new AdminDashboard(adminListener);

            // Employee Management
            empMgmtPanel = new EmployeeManagementPanel(adminListener);
            empMgmtPanel.loadEmployees(employees);

            // Payroll History Panel
            payrollHistoryPanel = new PayrollHistoryPanel("All Employees Payroll History");

            // Tax Rule Panel
            taxRulePanel = new TaxRulePanel(e -> {
                try {
                    double pf = taxRulePanel.getPf();
                    double taxPercent = taxRulePanel.getTax();
                    double bonus = taxRulePanel.getBonus();
                    double overtime = taxRulePanel.getOvertime();
                    taxController.updateTaxRule(pf, taxPercent, bonus, overtime);
                    JOptionPane.showMessageDialog(frame, "Tax rules updated successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid values!");
                }
            }, adminListener);

            // Add all panels to mainPanel
            mainPanel.add(welcomeScreen, "welcome");
            mainPanel.add(adminLoginPanel, "adminLogin");
            mainPanel.add(empLoginPanel, "empLogin");
            mainPanel.add(adminDashboard, "adminDashboard");
            mainPanel.add(empMgmtPanel, "empMgmt");
            mainPanel.add(payrollHistoryPanel, "payrollHistory");
            mainPanel.add(taxRulePanel, "taxRules");

            frame.setContentPane(mainPanel);
            showPanel("welcome");
            frame.setVisible(true);
        });
    }

    // Helper to switch panels
    private static void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    // --- Action Listeners ---

    // Admin dashboard navigation
    private static ActionListener adminListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if (src == adminDashboard.btnManageEmp) {
                empMgmtPanel.loadEmployees(employees);
                showPanel("empMgmt");
            } else if (src == adminDashboard.btnPayrollHistory) {
                payrollHistoryPanel = new PayrollHistoryPanel("All Employees Payroll History");
                payrollHistoryPanel.loadAllPayrolls(employees.values());
                payrollHistoryPanel.btnBack.addActionListener(adminListener);
                mainPanel.add(payrollHistoryPanel, "payrollHistory");
                showPanel("payrollHistory");
            } else if (src == adminDashboard.btnReports) {
                String[] columnNames = {"ID", "Name", "Position", "Salary", "Bonus"};
                Object[][] reportData = util.ReportGenerator.generatePayrollTable(employees);
                reportPanel = new ReportPanel("Payroll Report", reportData, columnNames, adminListener);
                mainPanel.add(reportPanel, "report");
                showPanel("report");
            } else if (src == adminDashboard.btnTaxRules) {
                taxRulePanel.loadValues(
                        taxRule.getDefaultPfPercent(),
                        taxRule.getDefaultTaxPercent(),
                        taxRule.getDefaultBonus(),
                        taxRule.getOvertimeRatePerHour()
                );
                showPanel("taxRules");
            } else if (src == adminDashboard.btnProcessPayroll) {
                payrollController.processPayrollForAll();
                JOptionPane.showMessageDialog(frame, "Payroll processed and payslips sent for all employees.");
                // Save employees after payroll processing
                try {
                    EmployeeFileUtil.saveEmployees(new ArrayList<>(employees.values()), EMPLOYEE_FILE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (src == adminDashboard.btnLogout) {
                showPanel("welcome");
            } else if (src == empMgmtPanel.btnBack) {
                showPanel("adminDashboard");
            } else if (src == empMgmtPanel.btnAdd) {
                // Add new employee dialog
                Employee emp = getEmployeeFromDialog(null);
                if (emp != null) {
                    if (employeeController.addEmployee(emp)) {
                        JOptionPane.showMessageDialog(frame, "Employee added.");
                        empMgmtPanel.loadEmployees(employees);
                        // Save after add
                        try {
                            EmployeeFileUtil.saveEmployees(new ArrayList<>(employees.values()), EMPLOYEE_FILE);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Employee ID already exists!");
                    }
                }
            } else if (src == empMgmtPanel.btnEdit) {
                int row = empMgmtPanel.table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(frame, "Select an employee to edit.");
                    return;
                }
                String id = (String) empMgmtPanel.tableModel.getValueAt(row, 0);
                Employee emp = employees.get(id);
                Employee updated = getEmployeeFromDialog(emp);
                if (updated != null) {
                    employeeController.editEmployee(id,
                            updated.getName(), updated.getPosition(), updated.getBaseSalary(),
                            updated.getPfPercent(), updated.getTaxPercent(), updated.getBonus(),
                            updated.getOvertimeHours());
    //                 employeeController.editEmployee(id,
    // updated.getName(), updated.getPosition(), updated.getBaseSalary(),
    // updated.getPfPercent(), updated.getTaxPercent(), updated.getBonus(),
    // updated.getOvertimeHours(), updated.getOvertimeCount());
                    JOptionPane.showMessageDialog(frame, "Employee updated.");
                    empMgmtPanel.loadEmployees(employees);
                    // Save after edit
                    try {
                        EmployeeFileUtil.saveEmployees(new ArrayList<>(employees.values()), EMPLOYEE_FILE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (src == payrollHistoryPanel.btnBack) {
                showPanel("adminDashboard");
            } else if (src == taxRulePanel.btnBack) {
                showPanel("adminDashboard");
            } else if (reportPanel != null && src == reportPanel.btnBack) {
                showPanel("adminDashboard");
            }
        }
    };

    // Employee dashboard navigation
    private static ActionListener empListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if (src == employeeDashboard.btnViewPayslip) {
                Payroll payroll = payrollController.processPayrollForEmployee(currentEmployee.getId());
                String msg = "Payslip for " + payroll.getDate().getMonth() + " " + payroll.getDate().getYear() +
                        "\nNet Salary: " + payroll.getNetSalary();
                JOptionPane.showMessageDialog(frame, msg);
                // Save after payroll processed for employee
                try {
                    EmployeeFileUtil.saveEmployees(new ArrayList<>(employees.values()), EMPLOYEE_FILE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (src == employeeDashboard.btnPayrollHistory) {
                empPayrollHistoryPanel = new PayrollHistoryPanel("Your Payroll History");
                empPayrollHistoryPanel.loadPayrollHistory(
                    currentEmployee.getPayrollHistory(),
                    currentEmployee.getId(),
                    currentEmployee.getName()
                );
                empPayrollHistoryPanel.btnBack.addActionListener(empListener);
                mainPanel.add(empPayrollHistoryPanel, "empPayrollHistory");
                showPanel("empPayrollHistory");
            } else if (src == employeeDashboard.btnLogout) {
                showPanel("welcome");
            } else if (empPayrollHistoryPanel != null && src == empPayrollHistoryPanel.btnBack) {
                showPanel("employeeDashboard");
            }
        }
    };

    // Modern dialog for adding/editing employee
    private static Employee getEmployeeFromDialog(Employee emp) {
        ModernEmployeeDialog dialog = new ModernEmployeeDialog(frame, emp, emp != null);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Employee result = dialog.getEmployee();
            if (result == null) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
            return result;
        }
        return null;
    }
}