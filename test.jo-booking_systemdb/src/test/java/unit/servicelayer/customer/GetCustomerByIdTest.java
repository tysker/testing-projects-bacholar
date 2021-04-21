package unit.servicelayer.customer;

import datalayer.customer.ICustomerStorage;
import dto.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import servicelayer.customer.CustomerServiceException;
import servicelayer.customer.CustomerServiceImpl;
import servicelayer.customer.ICustomerService;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class GetCustomerByIdTest {

    // SUI (System Under Test)
    private ICustomerService customerService;

    // DOC (Depended-on Component)
    private ICustomerStorage storageMock;


    @BeforeAll
    public void beforeAll() throws SQLException {
        storageMock = mock(ICustomerStorage.class);
        customerService = new CustomerServiceImpl(storageMock);
        when(storageMock.getCustomerById(anyInt())).thenReturn(new Customer(1,"a", "b"));
    }

    @Test
    public void mustCallStorageWhenGettingACustomerById() throws CustomerServiceException, SQLException {
        var customerId = 1;
        var customer = customerService.getCustomerById(customerId);
        Assertions.assertEquals(customerId, customer.getId());
    };

}
