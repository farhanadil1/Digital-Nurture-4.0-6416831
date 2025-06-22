import java.util.Scanner;

public class TestStrategyPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PaymentContext context = new PaymentContext();

        while (true) {
            System.out.println("\nSelect Payment Method:");
            System.out.println("1. Credit Card");
            System.out.println("2. PayPal");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 3) {
                System.out.println("Exiting Payment System.");
                break;
            }

            System.out.print("Enter payment amount: Rs.");
            double amount = scanner.nextDouble();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Enter Credit Card Number: ");
                    String card = scanner.nextLine();
                    context.setStrategy(new CreditCardPayment(card));
                    break;
                case 2:
                    System.out.print("Enter PayPal Email: ");
                    String email = scanner.nextLine();
                    context.setStrategy(new PaypalPayment(email));
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    continue;
            }

            context.makePayment(amount);
        }

        scanner.close();
    }
}
