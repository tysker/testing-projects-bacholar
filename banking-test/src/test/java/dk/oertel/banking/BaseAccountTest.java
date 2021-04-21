package dk.oertel.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class BaseAccountTest {
  private Bank bank;
  private Customer customer;
  private SmsService smsServiceMock;

  @BeforeEach
  public void setup() {
    bank = new BankMock();
    customer = new CustomerMock();
    smsServiceMock = mock(SmsService.class);
    }

  @Test
  public void testCreateAccount() {
    String number = "ABC-123";
    BaseAccount account = new BaseAccount(smsServiceMock, bank, customer, number);
    assertNotNull(account);
    }

  @Test
  public void testCreatedAccountHasBank() {
    String number = "ABC-123";
    BaseAccount account = new BaseAccount(smsServiceMock, bank, customer, number);
    assertEquals(bank, account.getBank());
    }

  @Test
  public void testCreatedAccountHasCustomer() {
    String number = "ABC-123";
    BaseAccount account = new BaseAccount(smsServiceMock, bank, customer, number);
    assertEquals(customer, account.getCustomer());
    }

  @Test
  public void testCreatedAccountHasNumber() {
    String number = "ABC-123";
    BaseAccount account = new BaseAccount(smsServiceMock, bank, customer, number);
    assertEquals(number, account.getNumber());
    }

  @Test
  public void testCreatedAccountHasZeroBalance() {
    String number = "ABC-123";
    BaseAccount account = new BaseAccount(smsServiceMock, bank, customer, number);
    assertEquals(0L, account.getBalance());
    }

  @Test
  public void testCreatedAccountHasEmptyTransactions() {
    String number = "ABC-123";
    BaseAccount account = new BaseAccount(smsServiceMock, bank, customer, number);
    assertEquals(0, account.getTransactions().size());
    }

  }
