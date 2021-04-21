package unit.servicelayer.notifications;

import dto.SmsMessage;
import org.junit.jupiter.api.*;
import servicelayer.booking.BookingServiceImp;
import servicelayer.booking.SmsServiceException;
import servicelayer.notifications.SmsService;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class SendSmsMessageTest {

    // SUI (System Under Test)
    private BookingServiceImp bookingService;

    // DOC (Depended-on Component)
    private SmsService smsMock;

    @BeforeAll
    public void beforeAll() throws SmsServiceException {
        smsMock = mock(SmsService.class);
        bookingService = new BookingServiceImp(smsMock);
    }

    @Test
    public void ifSmsIsSuccessfullyReturnTrue() throws SmsServiceException {
        var recipient = "0045-257896-258";
        var message = "hello world!";
        var sms = new SmsMessage(recipient, message);

        Assertions.assertTrue(bookingService.sendSms(sms, sms));
    }

    @Test
    public void ifSmsIsNotSuccessfullyReturnFalse() throws SmsServiceException {
        var recipient = "0045-257896-258";
        var message = "hello world!";
        var sms = new SmsMessage(recipient, message);
        var differentSms = new SmsMessage("0044-123456789-987", "hello europe");

        Assertions.assertFalse(bookingService.sendSms(sms, differentSms));
    }

}
