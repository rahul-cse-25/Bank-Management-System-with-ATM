
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BankServices {
	HashSet<Customers> customer = new HashSet<>();
	private static int totalCustomer;

	public int getAdminId() {
		return adminId;
	}

	public String getAdminPassWord() {
		return adminPassWord;
	}

	private int adminId = 0000;
	private String adminPassWord = "rahul@2024";

//	From line 25 to 49 is used to make default Account if want it then uncomment this

//	Customers ctm1 = new Customers("Rahul", 23, "male", "2000-09-20", "33180100018268", "9812345678", "Vasai(W)",
//			"virar", 10000, "6255-2854-1234", "1234");
//	Customers ctm2 = new Customers("Abhishek", 15, "male", "2008-12-02", "33180100018269", "9812345678", "Vasai(W)",
//			"virar", 10000, "6255-2854-2345", "2345");
//	Customers ctm3 = new Customers("Manish", 21, "male", "2002-09-25", "33180100018260", "9812345678", "Vasai(W)",
//			"virar", 10000, "6255-2854-1010", "1010");
//	Customers ctm4 = new Customers("Kajal", 20, "female", "2003-02-24", "33180100018267", "9812345678", "Vasai(W)",
//			"virar", 32582, "6255-2854-1111", "1111");
//	Customers ctm5 = new Customers("Vedika", 20, "female", "2003-09-17", "33180100018266", "9812345678", "Vasai(W)",
//			"virar", 10000, "6255-2854-2222", "2222");
//
//	public BankServices() {
//		customer.add(ctm1);
//		totalCustomer++;
//		customer.add(ctm2);
//		totalCustomer++;
//		customer.add(ctm3);
//		totalCustomer++;
//		customer.add(ctm4);
//		totalCustomer++;
//		customer.add(ctm5);
//		totalCustomer++;
//	}

	public static int getTotalCustomer() {
		return totalCustomer;
	}

	public boolean isPresentByCardNumber(String atmCardNum) {
		for (Customers ctm : customer) {
			if (ctm.getAtmCardNum().substring(10).equals(atmCardNum)) {
				return true;
			}
		}
		return false;
	}

	public void createAccount(String name, String dob, String gender, String phoneNum, String branch, String address,
			double amount, String atmPinNum) {
		long accNum = ThreadLocalRandom.current().nextInt(11111, 99999);
		String accountNum = "331801000" + accNum;
		dob = dob.replace(dob.charAt(4), '-');
		dob = dob.replace(dob.charAt(7), '-');
		int age = calculateAge(dob);
		long atmCardNumber = ThreadLocalRandom.current().nextInt(1111, 9999);
		String atmCardNum = "6255-2854-" + atmCardNumber;
		Customers ctm = new Customers(name, age, gender, dob, accountNum, phoneNum, branch, address, amount, atmCardNum,
				atmPinNum);
		customer.add(ctm);
		totalCustomer++;
		ctm.accountCreationMessage();
	}

	public int calculateAge(String dob) {
		LocalDate birthDate = LocalDate.parse(dob);
		LocalDate currentDate = LocalDate.now();

		Period period = Period.between(birthDate, currentDate);

		return period.getYears();
	}

	public void getCustomerDetail(String accountNumber) {
		if (isPresent(accountNumber)) {
			for (Customers ctm : customer) {
				if (ctm.getAccountNum().substring(9).equals(accountNumber)) {
					System.out.println("\n--------------------------------");
					System.out.println("--------------------------------");
					System.out.println("|  Name         : " + ctm.getName());
					System.out.println("--------------------------------");
					System.out.println("|  Age          : " + ctm.getAge());
					System.out.println("--------------------------------");
					System.out.println("|  Gender       : " + ctm.getGender());
					System.out.println("--------------------------------");
					System.out.println("|  Dob          : " + ctm.getDob());
					System.out.println("--------------------------------");
					System.out.println("|  Account No.  : " + ctm.getAccountNum());
					System.out.println("--------------------------------");
					System.out.println("|  ATM Card No. : " + ctm.getAtmCardNum());
					System.out.println("--------------------------------");
					System.out.println("|  Balance (rs.): " + ctm.getBalance());
					System.out.println("--------------------------------");
					System.out.println("|  Phone No.    : " + ctm.getPhoneNum());
					System.out.println("--------------------------------");
					System.out.println("|  Branch       : " + ctm.getBranch());
					System.out.println("--------------------------------");
					System.out.println("|  Address      : " + ctm.getAddress());
					System.out.println("--------------------------------");
					System.out.println("--------------------------------\n");
				}
			}
		} else {
			System.out.println("Customer Not found with this Account Number!!");
		}
	}

	public boolean isPresent(String accNum) {
		for (Customers ctm : customer) {
			if (ctm.getAccountNum().substring(9).equals(accNum)) {
				return true;
			}
		}
		return false;
	}

	public void closeAccount(String accountNumber) {
		if (totalCustomer == 0) {
			System.out.println("This Bank does not have any Customers.");
			return;
		}
		if (isPresent(accountNumber)) {
			for (Customers ctm : customer) {
				if (ctm.getAccountNum().substring(9).equals(accountNumber)) {
					customer.remove(ctm);
					totalCustomer--;
					System.out.println("Account Closed Successfully !");
					return;
				}
			}
		} else {
			System.out.println("No Customer found in Database...");
		}
	}

	public void showAllAccounts() {
		System.out.println("This is the List of All Accounts:");
		for (Customers ctm : customer) {
			System.out.println("-----------------");
			System.out.println(ctm.getAccountNum());
		}
		System.out.println("-----------------");
	}

	public void showAllCustomers() {
		if (totalCustomer == 0) {
			System.out.println("No Customer in this Bank.");
		} else {
			for (Customers ctm : customer) {
				System.out.println("--------------------------------");
				System.out.println(ctm);
			}
			System.out.println("--------------------------------");
		}
	}

	Scanner sc = new Scanner(System.in);

	public void atmImplement(AtmSimulation atm) {

		int count = 2;
		while (count >= 0) {

			System.out.println("\n--------------------------------");
			System.out.println("Welcome to the ATM Machine");
			System.out.println("--------------------------------");

			String atmCardNum = getStringInput(sc, "Enter ATM Card last 4 Digit:\t");
			String atmPin = getStringInput(sc, "Enter ATM Pin:\t");

			System.out.println("--------------------------------");
			if (isValid(atmCardNum, atmPin, atm)) {
				System.out.println("\n--------------------------------");
				System.out.println("*********ATM Simulation*********");
				System.out.println("--------------------------------");

				System.out.println("Welcome,\n1.Check Balance\n2.Deposit\n3.WithDraw Money\n"
						+ "4.Mini Statement\n5.Change ATM Pin\n6.Change ATM Card Number\n7.Go to Bank\nEnter -1 to Exit");

				System.out.println("--------------------------------");
				int choice = getIntInput(sc, "Enter your Choice:\t");

				System.out.println("--------------------------------");
				switch (choice) {
				case 1: {
					double balance = atm.getBalance();
					System.out.println("Total Available Balance is: " + balance);
					break;
				}
				case 2: {
					double amount = getDoubleInput(sc, "Enter the amount:\t");
					depositByAtm(amount, atm);
					break;
				}
				case 3: {
					double amount = getDoubleInput(sc, "Enter the amount:\t");
					withDrawByAtm(amount, atm);
					break;
				}
				case 4: {
					String accNum = getStringInput(sc, "Enter your Account Number:\t");
					if (isPresent(accNum)) {
						passbookStatement(accNum);
					} else {
						System.out.println("Account Not found !");
					}
					break;
				}
				case 5: {
					String accNum = getStringInput(sc, "Enter Account Number:\t");
					String phone = getStringInput(sc, "Enter Phone Number:\t");

					if (atm.getAccountNum().substring(9).equals(accNum) && atm.getPhoneNum().equals(phone)) {
						String pin = getStringInput(sc, "Enter New Pin:\t");
						if (isValidAtmPinNum(pin)) {
							setAtmPinNum(pin, atm);
							System.out.println("ATM Pin Updated Successfully !");
						} else {
							System.out.println("Pin should be of 4 to 8 digit !");
						}
					} else {
						System.out.println("Wrong Data..");
					}
					break;
				}
				case 6: {
					String accNum = getStringInput(sc, "Enter Account last 5 Digit:\t");
					String phone = getStringInput(sc, "Enter Phone Number:\t");

					if (atm.getAccountNum().substring(9).equals(accNum) && atm.getPhoneNum().equals(phone)) {
						String cardNum = getStringInput(sc, "Enter New ATM Card last 4 digit:\t");
						if (isInterger(atmPin) && cardNum.length() == 4) {
							setAtmCardNumber(cardNum, atm);
							System.out.println("ATM Card Number Updated Successfully !");
						} else {
							System.out.println("Enter Only last 4 Digit of New Card Number !");
						}
					} else {
						System.out.println("Wrong Data..");
					}
					break;
				}
				case 7: {
					return;
				}
				case -1: {
					System.out.println("--------------------------------");
					System.out.println("Thank You !! Visit Again...");
					System.out.println("--------------------------------");
					System.exit(0);
				}
				default:
					System.out.println("Invalid Choice !!");
				}
			} else {
				if (count == 0) {
					System.out.println("Wrong Credentials..");
					System.out.println("Your Card is Blocked !!");
					System.out.println("--------------------------------");
					return;
				} else {
					System.out.println("Something is Wrong between Card Number or Pin Number !!");
					System.out.println("Only " + count--
							+ " times left to block the Card !!\nplease Enter Valid Card Number and Pin Number !!");

				}
			}
		}
	}

	public void passbookStatement(String accNum) {
		for (Customers ctm : customer) {
			if (ctm.getAccountNum().substring(9).equals(accNum)) {
				List<Customers.Transaction> transactions = ctm.getStatement();
				int size = transactions.size();

				System.out.println("Last 10 Transactions for Account Number: " + accNum);
				int startIndex = Math.max(0, size - 10); // Start index to ensure at most 10 transactions are printed

				for (int i = startIndex; i < size; i++) {
					Customers.Transaction transaction = transactions.get(i);
					System.out.println("--> " + transaction.getAmount() + " " + transaction.getDescription()
							+ " ,Balance: " + transaction.getBalance());
				}
				return;
			}
		}
		System.out.println("Account Not found !");
	}

	private boolean isValid(String atmCardNum, String atmPin, AtmSimulation atm) {
		for (Customers ctm : customer) {
			if (ctm.getAtmCardNum().substring(10).equals(atmCardNum) && ctm.getAtmPinNum().equals(atmPin)) {
				double balance = ctm.getBalance();
				String accountNum = ctm.getAccountNum();
				String phoneNum = ctm.getPhoneNum();
				atm.fetchData(balance, accountNum, phoneNum);
				return true;
			}
		}
		return false;
	}

	public void setAtmPinNum(String pin, AtmSimulation atm) {
		for (Customers ctm : customer) {
			if (ctm.getAccountNum().equals(atm.getAccountNum())) {
				ctm.setAtmPinNum(pin);
			}
		}
	}

	public void setAtmCardNumber(String cardNum, AtmSimulation atm) {
		for (Customers ctm : customer) {
			if (ctm.getAccountNum().equals(atm.getAccountNum())) {
				ctm.setAtmCardNum(cardNum);
			}
		}
	}

	public void depositByAtm(double amount, AtmSimulation atm) {
		for (Customers ctm : customer) {
			if (ctm.getAccountNum().equals(atm.getAccountNum())) {
				ctm.setBalance(amount + ctm.getBalance());
				ctm.addTransaction(amount, "Rupees Credited by Atm");
				System.out.println("Desopited Successfully !!");
				System.out.println("Total Available Balance is: " + ctm.getBalance());
			}
		}
	}

	public void withDrawByAtm(double amount, AtmSimulation atm) {
		for (Customers ctm : customer) {
			if (ctm.getAccountNum().equals(atm.getAccountNum())) {
				if (ctm.getBalance() >= amount) {
					ctm.setBalance(ctm.getBalance() - amount);
					atm.sendUpdatedBalance(ctm.getBalance());
					ctm.addTransaction(amount, "Rupees WithDrawn by Atm");
					System.out.println("WithDraw Successful !");
					System.out.println("Total Available Balance is: " + ctm.getBalance());
					System.out.println("Please Collect your Cash and Card !!");
				} else {
					System.out.println("Insufficient Balance !!");
				}
			}
		}

	}

	public void updateDetails(String accountNum) {
		System.out.println("\n--------------------------------");
		System.out.println("What do you want to update:");
		System.out.println("1.Phone Number\n2.Address\n3.ATM Pin\nPress 0 to Main Menu");
		System.out.println("--------------------------------");
		int choice = getIntInput(sc, "Enter your choice:\t");
		System.out.println("--------------------------------");
		switch (choice) {
		case 0: {
			return;
		}
		case 1: {
			String phoneNum = getStringInput(sc, "Enter your Phone Number:\t");
			boolean validPhoneNumber = isValidPhoneNumber(phoneNum);
			while (!validPhoneNumber) {
				System.out.println("Phone Number must be of 10 digit and starting from (6,7,8,9) ! ");
				phoneNum = getStringInput(sc, "Enter your Phone Number:\t");
				validPhoneNumber = isValidPhoneNumber(phoneNum);
			}
			updatePhoneNumber(accountNum, phoneNum);
			break;
		}
		case 2: {
			String address = getStringInput(sc, "Enter New Address Number:\t");
			updateAddress(accountNum, address);
			break;
		}
		case 3: {
			String preAtmPin = getStringInput(sc, "Enter Old ATM Pin Number:\t");
			String atmPinNum = getStringInput(sc, "Enter New ATM Pin Number:\t");
			boolean validAtmPin = isValidAtmPinNum(atmPinNum);
			while (!validAtmPin) {
				System.out.println("Atm Pin must be of 4 to 8 digit ");
				atmPinNum = getStringInput(sc, "Enter New ATM Pin Number:\t");
				validAtmPin = isValidAtmPinNum(atmPinNum);
			}
			updateAtmPin(accountNum, atmPinNum, preAtmPin);
			break;
		}
		default:
			System.out.println("Invalid Choice !!");
		}
	}

	private void updateAtmPin(String accountNum, String atmPin, String preAtmPin) {
		for (Customers ctm : customer) {
			if (ctm.getAccountNum().substring(9).equals(accountNum) && ctm.getAtmPinNum().equals(preAtmPin)) {
				ctm.setAtmPinNum(atmPin);
				System.out.println("ATM Pin Updated Successfully !");
				return;
			}
		}
		System.out.println("Something is Wrong !!");
	}

	private void updateAddress(String accountNum, String address) {
		for (Customers ctm : customer) {
			if (ctm.getAccountNum().substring(9).equals(accountNum)) {
				ctm.setAddress(address);
				System.out.println("Address Updated Successfully !");
			}
		}
	}

	private void updatePhoneNumber(String accountNum, String phone) {
		try {
			for (Customers ctm : customer) {
				if (ctm.getAccountNum().substring(9).equals(accountNum)) {
					ctm.setPhoneNum(phone);
					System.out.println("Number Updated Successfully !");
				}
			}
		} catch (Exception e) {
			System.out.println("Phone No. must be Integer.");
		}
	}

	public double getBalance(String accNum) {
		for (Customers ctm : customer) {
			if (ctm.getAccountNum().substring(9).equals(accNum)) {
				return ctm.getBalance();
			}
		}
		return 0;
	}

	public void depositByBank(String accNum, double amount) {
		for (Customers ctm : customer) {
			if (ctm.getAccountNum().substring(9).equals(accNum)) {
				ctm.setBalance(amount + ctm.getBalance());
				ctm.addTransaction(amount, "Rupees Credited by Bank");
				System.out.println("Desopited Successfully !!");
				System.out.println("Your Current Available Balance is: " + ctm.getBalance());
			}
		}
	}

	public void withDrawByBank(String accNum, double amount) {
		for (Customers ctm : customer) {
			if (ctm.getAccountNum().substring(9).equals(accNum)) {
				if (ctm.getBalance() >= amount) {
					ctm.setBalance(ctm.getBalance() - amount);
					ctm.addTransaction(amount, "Rupees WithDrawn by Bank");
					System.out.println("WithDrawn Successfully !!");
					System.out.println("Please Collect your Cash !");
					System.out.println("Your Current Available Balance is: " + ctm.getBalance());
				} else {
					System.out.println("Insufficient Balance !");
					System.out.println("Your Current Available Balance is: " + ctm.getBalance());
				}
			}
		}
	}

	private int getIntInput(Scanner scanner, String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				int temp = scanner.nextInt();
				scanner.nextLine();
				return temp;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter an integer.");
				scanner.nextLine(); // Consume the invalid input
			}
		}
	}

//	UseFul when we want to getDoubleInput type data
	private double getDoubleInput(Scanner scanner, String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				double temp = scanner.nextDouble();
				scanner.nextLine();
				return temp;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid decimal number.");
				scanner.nextLine(); // Consume the invalid input
			}
		}
	}

	private String getStringInput(Scanner scanner, String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}

//	private long getLongInput(Scanner scanner, String prompt) {
//		while (true) {
//			try {
//				System.out.print(prompt);
//				return scanner.nextLong();
//			} catch (InputMismatchException e) {
//				System.out.println("Invalid input. Please enter a valid decimal number.");
//				scanner.nextLine(); // Consume the invalid input
//			}
//		}
//	}

	public boolean isValidAdmin(int id, String pass) {
		if (id == adminId && adminPassWord.equals(pass))
			return true;
		return false;
	}

	public boolean isValidGender(String gender) {
		if (gender.equals("male") || gender.equals("female")) {
			return true;
		}
		return false;
	}

	public boolean isValidPhoneNumber(String phoneNum) {
		if (phoneNum.length() != 10) {
			return false;
		}
		if ((phoneNum.charAt(0) == '6' || phoneNum.charAt(0) == '7' || phoneNum.charAt(0) == '8'
				|| phoneNum.charAt(0) == '9') && phoneNum.length() == 10) {
			return true;
		}
		return false;
	}

	public boolean isValidAtmPinNum(String atmPinNum) {
		if (isInterger(atmPinNum) && atmPinNum.length() > 3 && atmPinNum.length() < 9) {
			return true;
		}
		return false;
	}

	private boolean isInterger(String atmPinNum) {
		int left = 0;
		if (atmPinNum.length() == 0) {
			return false;
		}
		while (left <= atmPinNum.length()) {

			if (left == atmPinNum.length()) {
				return true;
			}

			char ch = atmPinNum.charAt(left);
			int val = (int) ch;

			if (val < 58 && val > 47) {
				left++;
			} else {
				return false;
			}

		}
		return false;
	}

	public boolean isValidDob(String dob) {
		if (dob.length() < 10) {
			return false;
		}
		boolean validYear = false;
		boolean validMonth = false;
		boolean validDate = false;
		try {
			int year = Integer.parseInt(dob.substring(0, 4));
			validYear = year > 1900 && year < 2006;
			int month = Integer.parseInt(dob.substring(5, 7));
			validMonth = month > 0 && month < 13;
			int date = Integer.parseInt(dob.substring(8, 10));
			if (month == 2 && date < 30) {
				validDate = true;
			} else if (date > 0 && date < 32) {
				validDate = true;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		if (validDate && validMonth && validYear) {
			return true;
		}
		return false;
	}
}
