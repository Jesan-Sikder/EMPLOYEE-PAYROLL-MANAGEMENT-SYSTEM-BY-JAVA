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


public class Main {
    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;

    
    private static Admin admin;
    private static HashMap<String, Employee> employees;
    private static TaxRule taxRule;
    private static PayrollProcessor payrollProcessor;

 
    private static AuthenticationController authController;
    private static EmployeeController employeeController;
    private static PayrollController payrollController;
    private static TaxController taxController;

    private static WelcomeScreen welcomeScreen;
    private static LoginPanel adminLoginPanel, empLoginPanel;
    private static AdminDashboard adminDashboard;
    private static EmployeeDashboard employeeDashboard;
    private static EmployeeManagementPanel empMgmtPanel;
    private static PayrollHistoryPanel payrollHistoryPanel;
    private static PayrollHistoryPanel empPayrollHistoryPanel;
    private static ReportPanel reportPanel;
    private static TaxRulePanel taxRulePanel;

 
    private static Employee currentEmployee;

   
    private static final String EMPLOYEE_FILE = "employees.json";

    public static void main(String[] args) {
       
        admin = new Admin("hr628", "24115628", "HR Manager", "hr@company.com");

      
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

        for (Employee emp : employees.values()) {
            System.out.println("ID: " + emp.getId() + " | Password: " + emp.getPassword());
        }

        taxRule = new TaxRule(8.0, 15.0, 1000.0, 200.0); // Default
        payrollProcessor = new PayrollProcessor(taxRule);

       
        authController = new AuthenticationController(admin, employees);
        employeeController = new EmployeeController(employees);
        payrollController = new PayrollController(payrollProcessor, employees);
        taxController = new TaxController(taxRule);

       
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Employee Payroll Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 700);
            frame.setLocationRelativeTo(null);

            cardLayout = new CardLayout();
            mainPanel = new JPanel(cardLayout);

            
            welcomeScreen = new WelcomeScreen(
                e -> showPanel("adminLogin"),
                e -> showPanel("empLogin")
            );

          
            adminLoginPanel = new LoginPanel("Admin Login", e -> {
                String id = adminLoginPanel.getId();
                String password = adminLoginPanel.getPassword();
                if (authController.authenticateAdmin(id, password)) {
                    showPanel("adminDashboard");
                } else {
                    adminLoginPanel.showError("Invalid admin credentials!");
                }
            });

          
            empLoginPanel = new LoginPanel("Employee Login", e -> {
                String id = empLoginPanel.getId();
                String password = empLoginPanel.getPassword();
                Employee emp = authController.authenticateEmployee(id, password);
                if (emp != null) {
                    currentEmployee = emp;
                    
                    if (employeeDashboard != null) {
                        mainPanel.remove(employeeDashboard);
                    }
                    employeeDashboard = new EmployeeDashboard(emp.getName(), empListener);
                    mainPanel.add(employeeDashboard, "employeeDashboard");
                    showPanel("employeeDashboard");
                } else {
                    empLoginPanel.showError("Invalid employee ID or password!");
                }
            });

        
            adminDashboard = new AdminDashboard(adminListener);

       
            empMgmtPanel = new EmployeeManagementPanel(adminListener);
            empMgmtPanel.loadEmployees(employees);

          
            payrollHistoryPanel = new PayrollHistoryPanel("All Employees Payroll History");

           
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


    private static void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

  
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
            
                Employee emp = getEmployeeFromDialog(null);
                if (emp != null) {
                    if (employeeController.addEmployee(emp)) {
                        JOptionPane.showMessageDialog(frame, "Employee added.");
                        empMgmtPanel.loadEmployees(employees);
                   
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

                    JOptionPane.showMessageDialog(frame, "Employee updated.");
                    empMgmtPanel.loadEmployees(employees);
               
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

   
    private static ActionListener empListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if (src == employeeDashboard.btnPayslip) {
                Payroll payroll = payrollController.processPayrollForEmployee(currentEmployee.getId());
                String msg = "Payslip for " + payroll.getDate().getMonth() + " " + payroll.getDate().getYear() +
                        "\nNet Salary: " + payroll.getNetSalary();
                JOptionPane.showMessageDialog(frame, msg);
           
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
            } else if (src == employeeDashboard.btnSupport) {
       
                JTextArea msgArea = new JTextArea(5, 30);
                msgArea.setLineWrap(true);
                msgArea.setWrapStyleWord(true);
                Object[] msg = {
                    "Send email to rahman241-15-628@diu.edu.bd for help.",
                    "Describe your problem below:", new JScrollPane(msgArea)
                };
                int result = JOptionPane.showConfirmDialog(frame, msg, "Support", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Your message has been sent!");
                 
                }
            } else if (src == employeeDashboard.btnSettings) {
                JOptionPane.showMessageDialog(frame, "You are not allowed to edit anything here. Good luck!");
            } else if (src == employeeDashboard.btnProfile) {
              
                String profileInfo = String.format(
                    "Name: %s\nID: %s\nEmail: %s\nPosition: %s",
                    currentEmployee.getName(),
                    currentEmployee.getId(),
                    currentEmployee.getEmail() != null ? currentEmployee.getEmail() : "N/A",
                    currentEmployee.getPosition()
                );
                JOptionPane.showMessageDialog(frame, profileInfo, "Your Profile", JOptionPane.INFORMATION_MESSAGE);
            } else if (empPayrollHistoryPanel != null && src == empPayrollHistoryPanel.btnBack) {
                showPanel("employeeDashboard");
            }
        }
    };

  
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