public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(int id) {
        // Mocked data, in real scenario this would query a database
        if (id == 1) {
            return new Customer(1, "Adil Farhan");
        } else if (id == 2) {
            return new Customer(2, "Riya Sharma");
        } else {
            return null;
        }
    }
}
