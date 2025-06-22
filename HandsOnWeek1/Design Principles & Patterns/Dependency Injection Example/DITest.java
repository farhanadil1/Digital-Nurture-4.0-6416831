import java.util.Scanner;

public class DITest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CustomerRepository repo = new CustomerRepositoryImpl(); 
        CustomerService service = new CustomerService(repo);    
        System.out.print("Enter Customer ID to search: ");
        int id = sc.nextInt();

        service.findAndPrintCustomer(id);

        sc.close();
    }
}
