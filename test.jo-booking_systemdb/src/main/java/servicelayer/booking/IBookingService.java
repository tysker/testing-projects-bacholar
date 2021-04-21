package servicelayer.booking;

import dto.Booking;

import java.util.Collection;
import java.sql.Date;

public interface IBookingService {
    int createBooking(int customerId, int employeeId, Date date, String start, String end) throws BookingServiceException;
    Collection<Booking> getBookingsForCustomer(int customerId) throws BookingServiceException;
    Collection<Booking> getBookingsForEmployee(int employeeId) throws BookingServiceException;
}
