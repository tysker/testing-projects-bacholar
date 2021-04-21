package dk.oertel.banking;

public interface SmsService {
	boolean SendSms(Customer customer, String text);
}
