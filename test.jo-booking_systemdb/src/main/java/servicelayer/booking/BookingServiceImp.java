package servicelayer.booking;

import datalayer.booking.IBookingStorage;
import dto.Booking;
import dto.BookingCreation;
import dto.SmsMessage;
import servicelayer.notifications.SmsService;

import java.sql.SQLException;
import java.util.Collection;
import java.sql.Date;

public class BookingServiceImp implements IBookingService, SmsService {

    private IBookingStorage bookingStorage;
    private SmsService smsService;

    public BookingServiceImp(IBookingStorage bookingStorage) {
        this.bookingStorage = bookingStorage;
    }
    public BookingServiceImp(SmsService smsService) {this.smsService = smsService;}

    @Override
    public boolean sendSms(SmsMessage message, SmsMessage returnedMessage) throws SmsServiceException {
        if(message.equals(returnedMessage)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int createBooking(int customerId, int employeeId, Date date, String start, String end) throws BookingServiceException {
        try {
            return bookingStorage.createBooking(new BookingCreation(customerId, employeeId, date, start, end));
        } catch(SQLException throwables) {
            throw new BookingServiceException(throwables.getMessage());
        }
    }

    @Override
    public Collection<Booking> getBookingsForCustomer(int customerId) throws BookingServiceException {
        try {
            return bookingStorage.getBookingsForCustomer(customerId);
        } catch(SQLException throwables) {
            throw new BookingServiceException(throwables.getMessage());
        }
    }

    @Override
    public Collection<Booking> getBookingsForEmployee(int employeeId) throws BookingServiceException {
        try {
            return bookingStorage.getBookingsForEmployee(employeeId);
        } catch (SQLException throwables) {
            throw new BookingServiceException(throwables.getMessage());
        }
    }

}
