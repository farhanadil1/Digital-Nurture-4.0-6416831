import java.util.Scanner;

public class Management {
    private static Inventory inventory = new Inventory();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    getProduct();
                    break;
                case 5:
                    printInventory();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nInventory Management System");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. Get Product");
        System.out.println("5. Print Inventory");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addProduct() {
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); 
        Product product = new Product(productId, productName, quantity, price);
        inventory.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        String productId = scanner.nextLine();
        System.out.print("Enter New Product Name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter New Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter New Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        Product updatedProduct = new Product(productId, productName, quantity, price);
        inventory.updateProduct(productId, updatedProduct);
        System.out.println("Product updated successfully.");
    }

    private static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        String productId = scanner.nextLine();
        inventory.deleteProduct(productId);
        System.out.println("Product deleted successfully.");
    }

    private static void getProduct() {
        System.out.print("Enter Product ID to retrieve: ");
        String productId = scanner.nextLine();
        Product product = inventory.getProduct(productId);
        if (product != null) {
            System.out.println("Retrieved Product: " + product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void printInventory() {
        System.out.println(inventory);
    }
}