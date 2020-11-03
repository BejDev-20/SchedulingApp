import model.Customer;

import java.util.List;

public interface CustomerDao {
    public List<Customer> allCustomers;

    public List<Customer> getAllCustomers(){
        return allCustomers;
    }
}
