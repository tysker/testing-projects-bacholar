package unit.servicelayer.employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datalayer.booking.IBookingStorage;
import dto.Booking;
import org.junit.jupiter.api.*;
import servicelayer.booking.BookingServiceException;
import servicelayer.booking.BookingServiceImp;
import servicelayer.booking.IBookingService;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreateBookingsByEmployeeId {

    // SUI (System Under Test)
    private IBookingService bookingService;

    // DOC (Depended-on Component)
    private IBookingStorage storageMock;

    private List<Booking> list = new ArrayList<>();

    @BeforeAll
    public void beforeAll() throws SQLException {
        storageMock = mock(IBookingStorage.class);
        bookingService = new BookingServiceImp(storageMock);
        list.add(new Booking(1,1,1, new Date(123456L), "12:00:00","14:00:00"));
        when(storageMock.getBookingsForEmployee(anyInt())).thenReturn(list);
    }

    @Test
    public void getBookingsByEmployeeId() throws BookingServiceException {
        var employeeId = 1;
        List<Booking> booking = (List) bookingService.getBookingsForEmployee(employeeId);
        Assertions.assertEquals(employeeId, booking.get(0).getEmployeeId());
    }
}
