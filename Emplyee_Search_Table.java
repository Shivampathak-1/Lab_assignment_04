import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {
    private String employeeID;
    private String name;
    private int age;
    private double salary;

    public Employee(String employeeID, String name, int age, double salary) {
        this.employeeID = employeeID;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }
}

class EmployeeDatabase {
    private List<Employee> employees;

    public EmployeeDatabase() {
        employees = new ArrayList<>();
        // Initialize the employee database with sample data
        employees.add(new Employee("161E90", "Raman", 41, 56000));
        employees.add(new Employee("161F91", "Himadri", 38, 67500));
        employees.add(new Employee("161F99", "Jaya", 51, 82100));
        employees.add(new Employee("171E20", "Tejas", 30, 55000));
        employees.add(new Employee("171G30", "Ajay", 45, 44000));
    }

    public List<Employee> searchByAge(int targetAge) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() == targetAge) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> searchByName(String targetName) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(targetName)) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> searchBySalary(String operator, double targetSalary) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            double employeeSalary = employee.getSalary();
            if ((operator.equals(">") && employeeSalary > targetSalary) ||
                (operator.equals("<") && employeeSalary < targetSalary) ||
                (operator.equals(">=") && employeeSalary >= targetSalary) ||
                (operator.equals("<=") && employeeSalary <= targetSalary)) {
                result.add(employee);
            }
        }
        return result;
    }
}

public class Emplyee_Search_Table {
    public static void main(String[] args) {
        EmployeeDatabase employeeDatabase = new EmployeeDatabase();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Employee Search App!");

        while (true) {
            System.out.println("\nChoose a search parameter:");
            System.out.println("1. Age");
            System.out.println("2. Name");
            System.out.println("3. Salary (>, <, >=, <=)");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 4) {
                System.out.println("Exiting the program.");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter the age to search for: ");
                    int age = scanner.nextInt();
                    List<Employee> ageResults = employeeDatabase.searchByAge(age);
                    printResults(ageResults);
                    break;
                case 2:
                    System.out.print("Enter the name to search for: ");
                    String name = scanner.nextLine();
                    List<Employee> nameResults = employeeDatabase.searchByName(name);
                    printResults(nameResults);
                    break;
                case 3:
                    System.out.print("Enter the salary operator (>, <, >=, <=): ");
                    String operator = scanner.nextLine();
                    System.out.print("Enter the target salary: ");
                    double targetSalary = scanner.nextDouble();
                    List<Employee> salaryResults = employeeDatabase.searchBySalary(operator, targetSalary);
                    printResults(salaryResults);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printResults(List<Employee> results) {
        if (results.isEmpty()) {
            System.out.println("No matching records found.");
        } else {
            System.out.println("\nMatching records:");
            double totalSalary = 0;
            for (Employee employee : results) {
                System.out.println("Employee ID: " + employee.getEmployeeID());
                System.out.println("Name: " + employee.getName());
                System.out.println("Age: " + employee.getAge());
                System.out.println("Salary: " + employee.getSalary());
                totalSalary += employee.getSalary();
                System.out.println();
            }
            double averageSalary = totalSalary / results.size();
            System.out.println("Average Salary: " + averageSalary);
        }
    }
}
