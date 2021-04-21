package servicelayer.customer;

import dto.Customer;

import java.util.Collection;
import java.util.Date;

public interface ICustomerService {
    int createCustomer(String firstName, String lastName, Date birthdate) throws CustomerServiceException;
    Customer getCustomerById(int id) throws CustomerServiceException;
    Collection<Customer> getCustomersByFirstName(String firstName) throws CustomerServiceException;
}
