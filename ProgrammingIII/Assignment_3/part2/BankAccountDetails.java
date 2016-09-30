package part2;

//CT326 - Programming III
//Author - Michael Neely 13100590

import java.io.IOException;
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
	
	//Default Constructor
	public BankAccountDetails(){
		//do nothing...
	}
	
	//Override Default WriteObject Method
	private void writeObject(java.io.ObjectOutputStream os) throws IOException {
		//Write Name (as UTF String)
        os.writeUTF(this.name);
        //Write Account Number
        os.writeInt(this.accountNumber);
        //Write Balance
        os.writeDouble(this.balance);
        //Prove Overridden Function was Successfully Called
        System.out.println("Overriden Write Object Successfully Called");
        System.out.println("Writing: " + this.toString());
    }
	
	//Override Default ReadObject Method
	private void readObject(java.io.ObjectInputStream is) throws IOException, ClassNotFoundException {
		//Read Name (as String)
		this.name = (String) is.readUTF();
		//Read Account Number
		this.accountNumber = is.readInt();
		//Read Balance
		this.balance = is.readDouble();
		//Prove Overridden Function was Successfully Called
		System.out.println("Overriden Read Object Successfully Called");
		System.out.println("Read: " + this.toString());
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
