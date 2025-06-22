import java.util.Scanner;

public class MVCTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Student Grade: ");
        String grade = scanner.nextLine();

        Student model = new Student(name, id, grade);
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);

       
        controller.updateView();

        // Modify data
        System.out.print("\nUpdate Name (optional): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            controller.setStudentName(newName);
        }

        System.out.print("Update Grade (optional): ");
        String newGrade = scanner.nextLine();
        if (!newGrade.isEmpty()) {
            controller.setStudentGrade(newGrade);
        }

        // Display updated info
        controller.updateView();

        scanner.close();
    }
}
