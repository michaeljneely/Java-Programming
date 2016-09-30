package part1;

//CT326 - Programming III
//Author - Michael Neely 13100590

import java.io.Serializable;

@SuppressWarnings("serial")
public class BankAccountDetails implements Serializable {

	// Static Integer to Provide Unique Account Numbers
	private static int accountNumberPool = 10000000;

	// Member Variables
	private String name;
	private int accountNumber;
	private double balance;

	// Constructor -> Takes Name + Balance
	public BankAccountDetails(String name, double balance) {
		this.name = name;
		// Account number assigned from pool which is then incremented
		this.accountNumber = accountNumberPool++;
		this.balance = balance;
	}

	// Print out all variables
	public String toString() {
		return ("Account Number " + this.accountNumber + " is owned by "
				+ this.name + ". Balance: $" + this.balance);
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
