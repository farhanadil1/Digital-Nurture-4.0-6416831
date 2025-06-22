public class CustomerService {
    private CustomerRepository customerRepository;

    // Constructor Injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void findAndPrintCustomer(int id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer != null) {
            System.out.println("Customer Found: " + customer);
        } else {
            System.out.println("Customer with ID " + id + " not found.");
        }
    }
}
