package util;

import java.util.HashMap;
import java.util.Random;
import model.Employee;

public class DataGenerator {
    private static final String[] POSITIONS = {"Developer", "Manager", "HR", "Analyst", "QA", "Support"};
    private static final String[] FIRST_NAMES = {"John", "Jane", "Alex", "Emily", "Chris", "Sophia", "Sam", "Olivia", "Max", "Ava"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Brown", "Williams", "Jones", "Garcia", "Miller", "Davis"};

    public static HashMap<String, Employee> generateEmployees(int count) {
        HashMap<String, Employee> employees = new HashMap<>();
        Random rand = new Random();
        for (int i = 1; i <= count; i++) {
            String firstName = FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
            String name = firstName + " " + lastName;
            String position = POSITIONS[rand.nextInt(POSITIONS.length)];
            double baseSalary = 30000 + rand.nextInt(40001); // 30k to 70k
            double pfPercent = 5 + rand.nextDouble() * 5; // 5% to 10%
            double taxPercent = 10 + rand.nextDouble() * 10; // 10% to 20%
            double bonus = 500 + rand.nextInt(1501); // 500 to 2000
            int overtimeHours = rand.nextInt(21); // 0 to 20
            String id = "E" + String.format("%03d", i);
            String password = "pass" + (1000 + rand.nextInt(9000));
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + i + "@company.com";

            Employee emp = new Employee(id, password, name, email, position, baseSalary, pfPercent, taxPercent, bonus, overtimeHours);
            employees.put(id, emp);
        }
        return employees;
    }
}