package unit.servicelayer.employee;

import datalayer.employee.IEmployeeStorage;
import dto.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.employee.EmployeeServiceException;
import servicelayer.employee.EmployeeServiceImp;
import servicelayer.employee.IEmployeeService;

import java.sql.SQLException;
import java.util.Date;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreateEmployeeTest {

    // SUT (System Under Test)
    private IEmployeeService iEmployeeService;

    // DOC (Depended-on Component)
    private IEmployeeStorage storageMock;


    @BeforeAll
    public void beforeAll(){
        storageMock = mock(IEmployeeStorage.class);
        iEmployeeService = new EmployeeServiceImp(storageMock);
    }

    @Test
    public void mustCallStorageWhenCreatingEmployee() throws EmployeeServiceException, SQLException {

        var firstName = "a";
        var lastName = "b";
        var birthdate = new Date(123456789l);
        iEmployeeService.createEmployee(firstName, lastName, birthdate);

        verify(storageMock, times(1))
                .createEmployee(
                        argThat(x -> x.firstname.equals(firstName) &&
                                x.lastname.equals(lastName)));

    }
}
