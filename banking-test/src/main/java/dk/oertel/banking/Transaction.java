package dk.oertel.banking;

public class Transaction {

	private final BaseAccount source;
	private final BaseAccount target;
	private final long amount;
	
	public Transaction(BaseAccount source, long amount, BaseAccount target) {
		this.source = source;
		this.target = target;
		this.amount = amount;
	}

	public BaseAccount getSource() {
		return source;
	}

	public BaseAccount getTarget() {
		return target;
	}

	public Long getAmount() {
		return amount;
	}

}
