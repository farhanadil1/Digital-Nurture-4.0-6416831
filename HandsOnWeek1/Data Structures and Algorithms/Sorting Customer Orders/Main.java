import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of orders: ");
        int numOrders = scanner.nextInt();
        scanner.nextLine(); 
        Order[] orders = new Order[numOrders];

        for (int i = 0; i < numOrders; i++) {
            System.out.print("Enter order ID for order " + (i + 1) + ": ");
            String orderId = scanner.nextLine();
            System.out.print("Enter customer name for order " + (i + 1) + ": ");
            String customerName = scanner.nextLine();
            System.out.print("Enter total price for order " + (i + 1) + ": ");
            double totalPrice = scanner.nextDouble();
            scanner.nextLine(); 

            orders[i] = new Order(orderId, customerName, totalPrice);
        }

        System.out.println("\nOrders before sorting:");
        printOrders(orders);

        System.out.println("\nChoose sorting algorithm \n1. Bubble Sort\n2. Quick Sort\nEnter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                BubbleSort.bubbleSort(orders);
                System.out.println("\nOrders after Bubble Sort:");
                break;
            case 2:
                QuickSort.quickSort(orders, 0, orders.length - 1);
                System.out.println("\nOrders after Quick Sort:");
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        printOrders(orders);
    }

    private static void printOrders(Order[] orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}