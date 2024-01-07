import java.util.InputMismatchException;
import java.util.Scanner;

public class BankImplements {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AtmSimulation atm = new AtmSimulation();
		BankServices bankServices = new BankServices();
		while (true) {
			System.out.println("\n--------------------------------");
			System.out.println("**********Bank Service**********");
			System.out.println("--------------------------------");
			String[] menuList = { "Create New Account", "Check Balance", "Deposit Money", "WithDraw Money",
					"Get your Details", "Close your Account", "Update your Details", "Passbook", "Go to ATM",
					"Total Bank Customers", "Show All Customer Accounts", "Show All Customers Details",
					"Enter -1 to Exit" };
			for (int i = 0; i < menuList.length; i++) {
				if (i == menuList.length - 1) {
					System.out.println(menuList[i]);
				} else {
					System.out.println(i + 1 + "." + menuList[i]);
				}
			}
			System.out.println("--------------------------------");
			int choice = getIntInput(sc, "Enter your Choice:\t");
			System.out.println("--------------------------------");
			switch (choice) {
			case 1: {
				System.out.println("Fill the Form:\t");

				String name = getStringInput(sc, "Enter your Name:\t");

				String dob = getStringInput(sc, "Enter your Date of birth (in this order : yyyy-MM-dd):\t");
				boolean validDob = bankServices.isValidDob(dob);
				while (!validDob) {
					System.out.println("DOB must be in this order : (yyyy-MM-dd)");
					dob = getStringInput(sc, "Enter your Date of birth (in this order : yyyy-MM-dd):\t").toLowerCase();
					validDob = bankServices.isValidDob(dob);
				}

				String gender = getStringInput(sc, "Enter your Gender(male/female):\t").toLowerCase();
				boolean validGen = bankServices.isValidGender(gender);
				while (!validGen) {
					System.out.println("Gender must be (male/female):");
					gender = getStringInput(sc, "Enter your Gender(male/female):\t").toLowerCase();
					validGen = bankServices.isValidGender(gender);
				}

				String branch = getStringInput(sc, "Enter your Current Branch:\t");

				String address = getStringInput(sc, "Enter your Address:\t");

				String phoneNum = getStringInput(sc, "Enter your Phone Number:\t");
				boolean validPhoneNumber = bankServices.isValidPhoneNumber(phoneNum);
				while (!validPhoneNumber) {
					System.out.println("Phone Number must be of 10 digit and starting from (6,7,8,9) ! ");
					phoneNum = getStringInput(sc, "Enter your Phone Number:\t");
					validPhoneNumber = bankServices.isValidPhoneNumber(phoneNum);
				}

				double amount = getDoubleInput(sc, "Enter your Amount to deposit:\t");
				sc.nextLine();

				String atmPinNum = getStringInput(sc, "Choose ATM Pin Number:\t");
				boolean validAtmPin = bankServices.isValidAtmPinNum(atmPinNum);
				while (!validAtmPin) {
					System.out.println("Atm Pin must be of 4 to 8 digit ");
					atmPinNum = getStringInput(sc, "Choose ATM Pin Number:\t");
					validAtmPin = bankServices.isValidAtmPinNum(atmPinNum);
				}

				if (validAtmPin && validGen && validPhoneNumber) {
					bankServices.createAccount(name, dob, gender, phoneNum, branch, address, amount, atmPinNum);
				}
				break;
			}
			case 2: {
				String accNum = getStringInput(sc, "Enter your Account's Last 5 Digit:\t");
				if (bankServices.isPresent(accNum)) {
					System.out.println("Your Current Available Balance is: " + bankServices.getBalance(accNum));
				} else {
					System.out.println("Acconut Not found!\nPlease Create New Account");
				}
				break;
			}
			case 3: {
				String accNum = getStringInput(sc, "Enter your Account's Last 5 Digit:\t");

				if (bankServices.isPresent(accNum)) {
					System.out.print("Enter your Amount to Deposit:\t");
					double amount = sc.nextDouble();
					bankServices.depositByBank(accNum, amount);
				} else {
					System.out.println("Acconut Not found!\nPlease Create New Account");
				}
				break;
			}
			case 4: {
				String accNum = getStringInput(sc, "Enter your Account's Last 5 Digit:\t");
				if (bankServices.isPresent(accNum)) {
					System.out.print("Enter your Amount to Withdraw:\t");
					double amount = sc.nextDouble();
					bankServices.withDrawByBank(accNum, amount);
				} else {
					System.out.println("Acconut Not found!\nPlease Create New Account");
				}
				break;
			}
			case 5: {
				String accountNumber = getStringInput(sc, "Enter your Account's Last 5 Digit:\t");
				bankServices.getCustomerDetail(accountNumber);
				break;
			}
			case 6: {
				String accountNumber = getStringInput(sc, "Enter your Account's Last 5 Digit:\t");
				bankServices.closeAccount(accountNumber);
				break;
			}
			case 7: {
				String accountNumber = getStringInput(sc, "Enter your Account's Last 5 Digit:\t");
				bankServices.updateDetails(accountNumber);
				break;
			}
			case 8: {
				String accNum = getStringInput(sc, "Enter your Account's Last 5 Digit:\t");
				if (bankServices.isPresent(accNum)) {
					bankServices.passbookStatement(accNum);
				} else {
					System.out.println("Account Not found !");
				}
				break;
			}
			case 9: {
				bankServices.atmImplement(atm);
				break;
			}
			case 10: {
				int id = getIntInput(sc, "Enter Admin Id:\t");
				String pass = getStringInput(sc, "Enter Admin Password:\t");

				if (bankServices.isValidAdmin(id, pass)) {
					System.out.println("Total Customers are: " + BankServices.getTotalCustomer());
				} else {
					System.out.println("Only for Admin ! Enter Correct Credentials");
				}
				break;
			}
			case 11: {
				int id = getIntInput(sc, "Enter Admin Id:\t");
				String pass = getStringInput(sc, "Enter Admin Password:\t");

				if (bankServices.isValidAdmin(id, pass)) {
					bankServices.showAllAccounts();
				} else {
					System.out.println("Only for Admin ! Enter Correct Credentials");
				}

				break;
			}
			case 12: {
				int id = getIntInput(sc, "Enter Admin Id:\t");
				String pass = getStringInput(sc, "Enter Admin Password:\t");

				if (bankServices.isValidAdmin(id, pass)) {
					bankServices.showAllCustomers();
				} else {
					System.out.println("Only for Admin ! Enter Correct Credentials");
				}

				break;
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
		}
	}

	public static int getIntInput(Scanner scanner, String prompt) {
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

	public static double getDoubleInput(Scanner scanner, String prompt) {
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

	public static String getStringInput(Scanner scanner, String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}

//	private static long getLongInput(Scanner scanner, String prompt) {
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
}
