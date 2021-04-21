package servicelayer.customer;

import datalayer.customer.ICustomerStorage;
import dto.Customer;
import dto.CustomerCreation;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class CustomerServiceImpl implements ICustomerService {

    private ICustomerStorage customerStorage;

    public CustomerServiceImpl(ICustomerStorage customerStorage) {
        this.customerStorage = customerStorage;
    }

    @Override
    public int createCustomer(String firstName, String lastName, Date birthdate) throws CustomerServiceException {
        try {
            return customerStorage.createCustomer(new CustomerCreation(firstName, lastName));
        } catch (SQLException throwables) {
            throw new CustomerServiceException(throwables.getMessage());
        }
    }

    @Override
    public Customer getCustomerById(int id) throws CustomerServiceException {
        try {
            return customerStorage.getCustomerById(id);
        } catch (SQLException throwables){
            throw new CustomerServiceException(throwables.getMessage());
        }

    }

    @Override
    public Collection<Customer> getCustomersByFirstName(String firstName) throws CustomerServiceException {
        try {
            return customerStorage.getCustomersByFirstName(firstName);
        }catch (SQLException throwables) {
            throw new CustomerServiceException(throwables.getMessage());
        }
    }
}
