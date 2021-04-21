package dk.oertel.banking;

public interface Account {
	void transfer(long amount, BaseAccount destination);
}
