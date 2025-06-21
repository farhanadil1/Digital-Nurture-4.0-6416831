package EcommerceSearch;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Book", "Education"),
            new Product(3, "Shoes", "Fashion"),
            new Product(4, "Phone", "Electronics"),
            new Product(5, "Watch", "Fashion")
        };

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name to search: ");
        String searchName = scanner.nextLine();

        //linear search
        System.out.println("\nLinear Search Result:");
        Product result1 = Searching.linearSearch(products, searchName);
        System.out.println(result1 != null ? result1 : "Product not found");

        //binary search
        Searching.simpleSort(products); //prerequisite to Sort array before binary search 
        System.out.println("\nBinary Search Result:");
        Product result2 = Searching.binarySearch(products, searchName);
        System.out.println(result2 != null ? result2 : "Product not found");

        scanner.close();
    }
}
