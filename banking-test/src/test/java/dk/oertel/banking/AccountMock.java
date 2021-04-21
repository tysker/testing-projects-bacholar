package dk.oertel.banking;

public class AccountMock implements Account {
	int count = 0;

	@Override
	public void transfer(long amount, BaseAccount destination) {
		count++;
	}

}
