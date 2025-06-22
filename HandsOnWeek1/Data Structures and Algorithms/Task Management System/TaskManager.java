import java.util.Scanner;

public class TaskManager {
    TaskNode head = null;
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.run();
    }

    void run() {
        while (true) {
            System.out.println("\nTask Management System");
            System.out.println("1. Add Task");
            System.out.println("2. Search Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    searchTask();
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    void addTask() {
        System.out.print("Enter Task ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Task Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Task Status: ");
        String status = sc.nextLine();

        Task newTask = new Task(id, name, status);
        TaskNode newNode = new TaskNode(newTask);

        if (head == null) {
            head = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

        System.out.println("Task added");
    }

    void searchTask() {
        System.out.print("Enter Task ID to search: ");
        int id = sc.nextInt();
        TaskNode temp = head;

        while (temp != null) {
            if (temp.task.taskId == id) {
                System.out.println("Found: " + temp.task);
                return;
            }
            temp = temp.next;
        }

        System.out.println("Task not found");
    }

    void viewTasks() {
        if (head == null) {
            System.out.println("No tasks to display");
            return;
        }

        TaskNode temp = head;
        while (temp != null) {
            System.out.println(temp.task);
            temp = temp.next;
        }
    }

    void deleteTask() {
        System.out.print("Enter Task ID to delete: ");
        int id = sc.nextInt();

        if (head == null) {
            System.out.println("Task list is empty");
            return;
        }

        if (head.task.taskId == id) {
            head = head.next;
            System.out.println("Task deleted");
            return;
        }

        TaskNode prev = head;
        TaskNode curr = head.next;

        while (curr != null) {
            if (curr.task.taskId == id) {
                prev.next = curr.next;
                System.out.println("Task deleted");
                return;
            }
            prev = curr;
            curr = curr.next;
        }

        System.out.println("Task not found");
    }
}
