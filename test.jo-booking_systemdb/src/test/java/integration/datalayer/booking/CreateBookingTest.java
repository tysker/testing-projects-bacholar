package integration.datalayer.booking;


import datalayer.booking.BookingStorageImpl;
import datalayer.booking.IBookingStorage;

import dto.BookingCreation;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
public class CreateBookingTest {
    private IBookingStorage bookingStorage;

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

        bookingStorage = new BookingStorageImpl(url+db, "root", "testuser123");

    }

    @Test
    public void mustSaveBookingInDatabaseWhenCallingCreateBooking() throws SQLException {
        // Arrange
        var customerId = 1;
        var employeeId = 1;
        var date = new Date(123456L);
        var start = "12:00:00";
        var end = "15:00:00";

        // Act
        bookingStorage.createBooking(new BookingCreation(customerId,employeeId, date, start, end));

        // Assert
        var bookings = bookingStorage.getBookingsForCustomer(customerId);
        assertTrue(
                bookings.stream().anyMatch(x ->
                        x.getStart().equals(start) &&
                                x.getEnd().equals(end)));
    }

    @Test
    public void mustReturnLatestId() throws SQLException {
        // Arrange
        // Act
        var id1 = bookingStorage.createBooking(new BookingCreation(1,1, new Date(987654L), "16:00:00", "20:00:00"));
        var id2 = bookingStorage.createBooking(new BookingCreation(1,1, new Date(258963L), "19:00:00", "22:00:00"));

        // Assert
        assertEquals(1, id2 - id1);
    }
}
