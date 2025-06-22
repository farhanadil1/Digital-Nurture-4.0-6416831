import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class LibraryManager {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "Java Programming", "James Gosling"),
            new Book(2, "Python Basics", "Guido van Rossum"),
            new Book(3, "C++ Primer", "Stanley Lippman"),
            new Book(4, "Effective Java", "Joshua Bloch")
        };

       

        System.out.print("Enter book title to search: ");
        String query = sc.nextLine();

        System.out.println("Search Method:");
        System.out.println("1. Linear Search");
        System.out.println("2. Binary Search");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); 

        switch (choice) {
            case 1:
                System.out.println("\nUsing Linear Search:");
                Book result1 = linearSearch(books, query);
                if (result1 != null) {
                    System.out.println("Found: " + result1);
                } else {
                    System.out.println("Book not found.");
                }
                break;

            case 2:
                Arrays.sort(books, Comparator.comparing(book -> book.title));
                System.out.println("\nUsing Binary Search (after sorting):");
                Book result2 = binarySearch(books, query);
                if (result2 != null) {
                    System.out.println("Found: " + result2);
                } else {
                    System.out.println("Book not found.");
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    static Book binarySearch(Book[] books, String title) {
        int low = 0, high = books.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(title);
            if (cmp == 0) return books[mid];
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }
}
