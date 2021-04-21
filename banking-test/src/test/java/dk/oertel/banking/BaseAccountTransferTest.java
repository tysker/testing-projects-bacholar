package dk.oertel.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BaseAccountTransferTest {
	private final String sourceAccountId = "ABC-123";
	private final String destinationAccountId = "XYZ-456";

	private BaseAccount source;
	private BaseAccount destination;

	private @Mock Bank bank;
	private @Mock SmsService smsServiceMock;
	private @Mock Customer customer;

	@BeforeEach
	public void setup() {
		// We could either use the @Mock annotations above, or we could instantiate
		// mocks manually:
		// bank = mock(Bank.class);
		// customer = mock(Customer.class);
		// smsServiceMock = mock(SmsService.class);

		source = new BaseAccount(smsServiceMock, bank, customer, sourceAccountId);
		destination = new BaseAccount(smsServiceMock, bank, customer, destinationAccountId);

		/*
		 * In the following two lines, notice that I have added lenient() in front of
		 * when(). Apparently, because of the @ExtendWith(MockitoExtension.class) in
		 * line 16 before the class declaration, Mockito uses something called Strict
		 * Stubbing. That means, it tries to detect common problems. One common problem
		 * is "unused stubs". Try to remove the lenient() below, and run the tests. They
		 * will fail with an error (read the error message!), because Mockito thinks
		 * this as an "unused stub", since "getAccount" is not called later in THIS
		 * method. Since Mockito was wrong this time -- we actually WANT to mock these
		 * two getAccount calls -- we add "lenient()." before when(), to make it relax a
		 * bit.
		 */
		lenient().when(bank.getAccount(sourceAccountId)).thenReturn(source);
		lenient().when(bank.getAccount(destinationAccountId)).thenReturn(destination);
	}

	@Test
	@Disabled("Disabled this requirement for now")
	public void mustSendSmsWhenSourceAccountHasInsufficientFunds() {
		// Act
		source.transfer(100_00, destinationAccountId);

		// Assert
		verify(smsServiceMock, times(1)).SendSms(eq(customer), anyString());
	}

	@Test
	@Disabled("Disabled this requirement for now")
	public void mustThrowSmsFaultExceptionWhenSmsCouldNotBeSent() {
		// Arrange
		when(smsServiceMock.SendSms(any(Customer.class), anyString())).thenReturn(false);

		// Act, Assert
		assertThrows(SmsFaultException.class, () -> source.transfer(100_00, destinationAccountId));
	}

	@Test
	public void mustThrowWhenDestinationAccountDoesNotExist() {
		// Arrange
		String nonExistingDestinationId = "non-existing-id";
		when(bank.getAccount(nonExistingDestinationId)).thenReturn(null);

		// Act, Assert
		assertThrows(NonExistingAccount.class, () -> source.transfer(0, nonExistingDestinationId));
	}

	@DisplayName("source balance is updated on transfer")
	@Test
	public void testSourceBalanceIsUpdatedOnTransfer() {
		long balanceBefore = source.getBalance();
		long amount = 100_00;

		// Act
		source.transfer(amount, destination);

		assertEquals(balanceBefore - amount, source.getBalance());
	}

	@Test
	public void testDestinationBalanceIsUpdatedOnTransfer() {
		long balanceBefore = destination.getBalance();
		long amount = 100_00;

		source.transfer(amount, destination);
		assertEquals(balanceBefore + amount, destination.getBalance());
	}

	@Test
	public void testSourceTransactionListIsUpdatedOnTransfer() {
		long sizeBefore = source.getTransactions().size();
		long amount = 100_00;

		source.transfer(amount, destination);
		assertEquals(sizeBefore + 1, source.getTransactions().size());
	}

	@Test
	public void testDestinationTransactionListIsUpdatedOnTransfer() {
		long sizeBefore = destination.getTransactions().size();
		long amount = 100_00;

		source.transfer(amount, destination);
		assertEquals(sizeBefore + 1, destination.getTransactions().size());
	}

	@Test
	public void testSourceLastTransactionOnTransfer() {
		long amount = 100_00;
		source.transfer(amount, destination);
		Transaction last = source.getTransactions().get(source.getTransactions().size() - 1);
		assertEquals(amount, last.getAmount());
		assertEquals(source, last.getSource());
		assertEquals(destination, last.getTarget());
	}

	@Test
	public void testDestinationLastTransactionOnTransfer() {
		long amount = 100_00;
		source.transfer(amount, destination);
		Transaction last = destination.getTransactions().get(destination.getTransactions().size() - 1);
		assertEquals(amount, last.getAmount());
		assertEquals(source, last.getSource());
		assertEquals(destination, last.getTarget());
	}

	@Test
	public void testSourceBalanceIsUpdatedOnTransferByNumber() {
		long balanceBefore = source.getBalance();
		long amount = 200_00;
		when(bank.getAccount(destinationAccountId)).thenReturn(destination);

		source.transfer(amount, destinationAccountId);

		assertEquals(balanceBefore - amount, source.getBalance());
	}

	@Test
	public void testDestinationBalanceIsUpdatedOnTransferByNumber() {
		// Arrange
		String destinationNumber = "XYZ-789";
		when(bank.getAccount(destinationNumber)).thenReturn(destination);

		long balanceBefore = source.getBalance();
		long amount = 200_00;

		// Act
		source.transfer(amount, destinationNumber);

		assertEquals(balanceBefore + amount, destination.getBalance());
	}
}
