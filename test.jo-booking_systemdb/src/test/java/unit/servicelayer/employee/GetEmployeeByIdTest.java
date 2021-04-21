package unit.servicelayer.employee;

import datalayer.employee.IEmployeeStorage;
import dto.Employee;
import org.junit.jupiter.api.*;
import servicelayer.employee.EmployeeServiceException;
import servicelayer.employee.EmployeeServiceImp;
import servicelayer.employee.IEmployeeService;

import java.sql.Date;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class GetEmployeeByIdTest {

    // SUT (System Under Test)
    private IEmployeeService iEmployeeService;

    // DOC (Depended-on Component)
    private IEmployeeStorage storageMock;


    @BeforeAll
    public void beforeAll() throws SQLException {
        storageMock = mock(IEmployeeStorage.class);
        iEmployeeService = new EmployeeServiceImp(storageMock);
        when(storageMock.getEmployeeWithId(anyInt())).thenReturn(new Employee(1, "a", "b", new Date(123456789L)));
    }

    @Test
    public void mustCallStorageWhengettingAEmployeeById() throws EmployeeServiceException, SQLException {
        var employeeId = 1;
        var employee = iEmployeeService.getEmployeeById(employeeId);
        Assertions.assertEquals(employeeId, employee.getId());
    }
}
