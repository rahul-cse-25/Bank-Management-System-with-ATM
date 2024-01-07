import java.util.ArrayList;
import java.util.List;

public class Customers {
	private String name;
	private int age;
	private String gender;
	private String dob;
	private String accountNum;
	private String phoneNum;
	private String branch;
	private String address;
	private double balance;
	private String atmCardNum;
	private String atmPinNum;
	private List<Transaction> statement = new ArrayList<>();

	public class Transaction {
		private double amount;
		private double balance;
		private String description;

		public Transaction(double amount, double balance, String description) {
			this.amount = amount;
			this.balance = balance;
			this.description = description;
		}

		public double getAmount() {
			return amount;
		}

		public double getBalance() {
			return balance;
		}

		public String getDescription() {
			return description;
		}
	}

	public Customers(String name, int age, String gender, String dob, String accountNum, String phoneNum, String branch,
			String address, double balance, String atmCardNum, String atmPin) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.dob = dob;
		this.accountNum = accountNum;
		this.phoneNum = phoneNum;
		this.branch = branch;
		this.address = address;
		this.balance = balance;
		this.atmCardNum = atmCardNum;
		this.atmPinNum = atmPin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAtmCardNum() {
		return atmCardNum;
	}

	public void setAtmCardNum(String cardNum) {
		this.atmCardNum = cardNum;
	}

	public String getAtmPinNum() {
		return atmPinNum;
	}

	public void setAtmPinNum(String atmPinNum) {
		this.atmPinNum = atmPinNum;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void addTransaction(double amount, String description) {
		statement.add(new Transaction(amount, balance, description));
	}

	@Override
	public String toString() {
		return "Customers [name=" + name + ", age=" + age + ", gender=" + gender + ", dob=" + dob + ", accountNum="
				+ accountNum + ", phoneNum=" + phoneNum + ", branch=" + branch + ", address=" + address + ", balance="
				+ balance + ", atmCardNum=" + atmCardNum + "]";
	}

	public void accountCreationMessage() {
		System.out.println("--------------------------------");
		if (gender.equals("male")) {
			System.out.println("Mr. " + name + ",");
		} else if (gender.equals("female")) {
			System.out.println("Miss. " + name + ",");
		}
		System.out.println("Your Account Created Successfully.");
		System.out.println("Your Account Number: " + accountNum + "\nYour ATM Card Number: " + atmCardNum);
		System.out.println("--------------------------------");
	}

	public List<Customers.Transaction> getStatement() {
		return statement;
	}

}
