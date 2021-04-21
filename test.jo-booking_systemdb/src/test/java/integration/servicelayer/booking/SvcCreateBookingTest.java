package integration.servicelayer.booking;

import datalayer.booking.BookingStorageImpl;
import datalayer.booking.IBookingStorage;
import datalayer.customer.CustomerStorageImpl;
import datalayer.customer.ICustomerStorage;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import servicelayer.booking.BookingServiceException;
import servicelayer.booking.BookingServiceImp;
import servicelayer.booking.IBookingService;
import servicelayer.customer.CustomerServiceException;
import servicelayer.customer.CustomerServiceImpl;
import servicelayer.customer.ICustomerService;

import java.sql.SQLException;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
public class SvcCreateBookingTest {

    private IBookingService svc;
    private IBookingStorage storage;

    private static final int PORT = 3306;
    private static final String PASSWORD = "testuser1234";

    @Container
    public static MySQLContainer mysql = (MySQLContainer) new MySQLContainer(DockerImageName.parse("mysql"))
            .withPassword(PASSWORD)
            .withExposedPorts(PORT);

    // A generic container could be used as well:
//    public static GenericContainer mysql = new GenericContainer(DockerImageName.parse("mysql"))
//            .withExposedPorts(PORT)
//            .withEnv("MYSQL_ROOT_PASSWORD", PASSWORD);

    @BeforeAll
    public void setup() {
        System.err.println("mysql created: " + mysql.isCreated());
        System.err.println("mysql running: " + mysql.isRunning());
        System.err.println("mysql host: " + mysql.getHost());
        String url = "jdbc:mysql://"+mysql.getHost()+":"+mysql.getFirstMappedPort()+"/";
        String db = "DemoApplicationTest";
        Flyway flyway = new Flyway(
                new FluentConfiguration()
                        .schemas(db)
                        .defaultSchema(db)
                        .createSchemas(true)
                        .target("6")
                        .dataSource(url, "root", PASSWORD)
        );
        flyway.migrate();

        storage = new BookingStorageImpl(url + db,"root", PASSWORD);
        svc = new BookingServiceImp(storage);
    }

    @Test
    public void mustSaveBookingsToDatabaseWhenCallingCreateBookings() throws BookingServiceException, SQLException {
        // Arrange
        var customerId = 1;
        var employeeId = 1;
        var date = new Date(1239821l);
        var start = "12:00:00";
        var end = "13:00:00";
        int id = svc.createBooking(customerId, employeeId, date, start, end );

        // Act
        var createdBooking = storage.getBookingsForCustomer(id);
        var startStream = createdBooking.stream().findFirst();
        // Assert
        assertEquals(start, startStream.get().getStart() );
        assertEquals(end, startStream.get().getEnd());
    }
}
