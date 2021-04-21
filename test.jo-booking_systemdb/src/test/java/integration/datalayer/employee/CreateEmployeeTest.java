package integration.datalayer.employee;

import com.github.javafaker.Faker;
import datalayer.booking.BookingStorageImpl;
import datalayer.employee.EmployeeStorageImp;
import datalayer.employee.IEmployeeStorage;
import dto.BookingCreation;
import dto.CustomerCreation;
import dto.EmployeeCreation;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
public class CreateEmployeeTest {
    private IEmployeeStorage employeeStorage;

    @BeforeAll
    public void Setup() throws SQLException {
        var url = "jdbc:mysql://localhost:3307/";
        var db = "TestBookingSystemDB";

        Flyway flyway = new Flyway(new FluentConfiguration()
                .defaultSchema(db)
                .createSchemas(true)
                .schemas(db)
                .target("4")
                .dataSource(url, "root", "testuser123"));

        flyway.migrate();

        employeeStorage = new EmployeeStorageImp(url+db, "root", "testuser123");

    }

    @Test
    public void mustSaveEmployeeInDatabaseWhenCallingCreateEmployee() throws SQLException {
        // Arrange
        // Act
        int id = employeeStorage.createEmployee(new EmployeeCreation("John","Carlssonn"));

        // Assert
        var employee = employeeStorage.getEmployeeWithId(id);
        assertTrue(employee.getFirstname().equals("John"));
    }

    @Test
    public void mustReturnLatestId() throws SQLException {
        // Arrange
        // Act
        var id1 = employeeStorage.createEmployee(new EmployeeCreation("a", "b"));
        var id2 = employeeStorage.createEmployee(new EmployeeCreation("c", "d"));

        // Assert
        assertEquals(1, id2 - id1);
    }
}
