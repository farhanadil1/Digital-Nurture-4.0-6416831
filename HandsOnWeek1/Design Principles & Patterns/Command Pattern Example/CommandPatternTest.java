import java.util.Scanner;

public class CommandPatternTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        RemoteControl remote = new RemoteControl();

        while (true) {
            System.out.println("\nHome Automation Menu");
            System.out.println("1. Turn ON the light");
            System.out.println("2. Turn OFF the light");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    remote.setCommand(lightOn);
                    remote.pressButton();
                    break;
                case 2:
                    remote.setCommand(lightOff);
                    remote.pressButton();
                    break;
                case 3:
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
