package datalayer.customer;

import dto.Customer;
import dto.CustomerCreation;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerStorage {
    public Customer getCustomerById(int customerId) throws SQLException;
    public List<Customer> getCustomers() throws SQLException;
    public List<Customer> getCustomersByFirstName(String firstname) throws SQLException;
    public int createCustomer(CustomerCreation customerToCreate) throws SQLException;
}
