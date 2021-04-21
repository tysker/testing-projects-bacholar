package unit.servicelayer.customer;

import datalayer.customer.ICustomerStorage;
import dto.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import servicelayer.customer.CustomerServiceException;
import servicelayer.customer.CustomerServiceImpl;
import servicelayer.customer.ICustomerService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class GetCustomerByFirstNameTest {

    // SUT (System Under Test)
    private ICustomerService customerService;

    // DOC (Depended-on Component)
    private ICustomerStorage storageMock;

    private List<Customer> list = new ArrayList<>();

    @BeforeAll
    public void beforeAll() throws CustomerServiceException, SQLException {
        storageMock = mock(ICustomerStorage.class);
        customerService = new CustomerServiceImpl(storageMock);
        list.add(new Customer(1,"a","b"));
        when(storageMock.getCustomersByFirstName(anyString())).thenReturn(list);
    }

    @Test
    public void mustCallStorageWhenGettingACustomerByFirstName() throws CustomerServiceException {
        var firstname = "a";
        List<Customer> customers = (List) customerService.getCustomersByFirstName(firstname);
        Assertions.assertEquals(firstname, customers.get(0).getFirstname());
    }
}
