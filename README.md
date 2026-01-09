# Employee Payroll Management System

The project employs an MVC (Model-View-Controller) architecture for maintainability, scalability, and separation of concerns. The overall directory structure is as follows:

```
Employee Payroll Management System/
│
├── bin/                        # Compiled .class files (Java bytecode, same hierarchy as src/)
│
├── employees                   # JSON data file for employee records and payroll history
│
├── src/                        # Source code root
│   ├── controller/             # Controllers: Handle application logic and user input
│   │   ├── AuthenticationController.java
│   │   ├── EmployeeController.java
│   │   ├── PayrollController.java
│   │   └── TaxController.java
│   │
│   ├── model/                  # Models: Represent data and business logic
│   │   ├── Admin.java
│   │   ├── Employee.java
│   │   ├── Payroll.java
│   │   ├── PayrollProcessor.java
│   │   ├── TaxRule.java
│   │   └── User.java
│   │
│   ├── util/                   # Utilities: Helper classes and utilities
│   │   ├── DataGenerator.java
│   │   ├── EmailService.java
│   │   ├── ReportGenerator.java
│   │   ├── EmployeeFileUtil.java
│   │   └── LocalDateAdapter.java
│   │
│   └── view/                   # Views: GUI components (Java Swing)
│       ├── AdminDashboard.java
│       ├── EmployeeDashboard.java
│       ├── EmployeeManagementPanel.java
│       ├── LoginPanel.java
│       ├── ModernEmployeeDialog.java
│       ├── PayrollHistoryPanel.java
│       ├── ReportPanel.java
│       ├── TaxRulePanel.java
│       ├── WelcomeScreen.java
│       └── jesan.jpg           # Asset/Image used in the GUI
│
├── lib/                        # Third-party libraries
│   └── gson-2.10.1.jar         # Gson library for JSON serialization/deserialization
│
└── Main.java                   # Main entry point of the application
```

## Directory Descriptions

- bin/ 
  Contains compiled `.class` files, reflecting the source structure for execution.

- employees 
  JSON file used as the persistent data store for employee information and payroll records.

- src/
  Main source code folder, structured as follows:
  - controller/: Handles user input and orchestrates communication between view and model.
  - model/: Contains core business logic and data representation.
  - util/: Helper classes, utilities for data manipulation, email simulation, report generation, file operations, and data adaptation.
  - view/: GUI panels and windows constructed with Java Swing; handles all user interface elements.

- lib/ 
  External libraries required for the project (e.g., Gson for JSON processing).

- Main.java  
  The application's main class containing the `public static void main(String[] args)` method to launch the system.

---

This modular organization ensures the project is easy to understand, maintain, and extend, adhering to best practices in OOP and Java application development.