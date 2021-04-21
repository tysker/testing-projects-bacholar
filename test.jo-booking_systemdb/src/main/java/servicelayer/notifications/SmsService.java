package servicelayer.notifications;

import dto.SmsMessage;
import servicelayer.booking.SmsServiceException;

public interface SmsService {
    /**
     * Sends an sms message.
     * @param message
     * @return true if success, false otherwise
     */
    boolean sendSms(SmsMessage message, SmsMessage returnedMessage) throws SmsServiceException;
}
