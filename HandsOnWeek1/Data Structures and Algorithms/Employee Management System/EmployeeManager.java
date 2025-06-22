import java.util.Scanner;

public class EmployeeManager {
    private final int MAX_EMPLOYEES = 100;
    private Employee[] employees = new Employee[MAX_EMPLOYEES];
    private int count = 0;
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        manager.showMenu();
    }

    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    searchEmployee();
                    break;
                case 3:
                    viewEmployees();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addEmployee() {
        if (count >= MAX_EMPLOYEES) {
            System.out.println("Employee list is full.");
            return;
        }
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Position: ");
        String position = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        employees[count++] = new Employee(id, name, position, salary);
        System.out.println("Employee added.");
    }

    private void searchEmployee() {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt();

        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                System.out.println("Found: " + employees[i]);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    private void viewEmployees() {
        if (count == 0) {
            System.out.println("No employees in the system.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }

    private void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();

        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                System.out.println("Employee deleted.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }
}
