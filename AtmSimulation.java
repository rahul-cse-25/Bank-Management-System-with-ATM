
public class AtmSimulation {

	private double balance;
	private String accountNum;
	private String phoneNum;

	public double getBalance() {
		return balance;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void fetchData(double balance, String accountNum, String phoneNum) {
		this.balance = balance;
		this.accountNum = accountNum;
		this.phoneNum = phoneNum;
	}

	public void sendUpdatedBalance(double balance) {
		this.balance = balance;
	}

}
