package unit.servicelayer.booking;

import datalayer.booking.IBookingStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.booking.BookingServiceException;
import servicelayer.booking.BookingServiceImp;
import servicelayer.booking.IBookingService;
import servicelayer.customer.CustomerServiceException;

import java.sql.SQLException;
import java.sql.Date;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreateBookingTest {

    // SUI (System Under Test)
    private IBookingService bookingService;

    // DOC (Depended-on Component)
    private IBookingStorage storageMock;

    @BeforeAll
    public void beforeAll() {
        storageMock = mock(IBookingStorage.class);
        bookingService = new BookingServiceImp(storageMock);
    }

    @Test
    public void mustCallStorageWhenCreatingBooking() throws BookingServiceException, SQLException {

        var customerId = 1;
        var employeeId = 1;
        var date = new Date(123456L);
        var start = "11:00:00";
        var end = "12:00:00";

        bookingService.createBooking(customerId, employeeId, date, start, end);

        verify(storageMock, times(1))
                .createBooking(
                        argThat(x -> x.customerId == customerId &&
                                x.employeeId == employeeId &&
                                x.date == date &&
                                x.start.equals(start) &&
                                x.end.equals(end)
                                ));
    }
}
