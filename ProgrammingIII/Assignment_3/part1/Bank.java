package part1;

//CT326 - Programming III
//Author - Michael Neely 13100590

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

//Read and Write Bank Accounts from Text File
public class Bank {

	// Array List of Bank Account Details
	public List<BankAccountDetails> bank_accounts;

	// Constructor. Initialize Bank with data from above arrays
	public Bank(String[] names, double[] balances) {
		bank_accounts = new ArrayList<BankAccountDetails>();
		for (int i = 0; i < names.length; i++) {
			bank_accounts.add(new BankAccountDetails(names[i], balances[i]));
		}
	}

	// Write Bank to Text File
	public void writeBank() throws IOException {
		FileOutputStream out = new FileOutputStream("bankdetails_1.txt");
		ObjectOutputStream s = new ObjectOutputStream(out);
		//Write bank_accounts ArrayList
		s.writeObject(bank_accounts);
		//Flush and Close Output Streams
		s.flush();
		s.close();
		out.flush();
		out.close();
	}

	// Read Bank From Test File
	@SuppressWarnings("unchecked")
	public void readBank() throws IOException, ClassNotFoundException {
		FileInputStream in = new FileInputStream("bankdetails_1.txt");
		ObjectInputStream s = new ObjectInputStream(in);
		//Read bank_accounts Array List using explicit cast
		bank_accounts = (ArrayList<BankAccountDetails>) s.readObject();
		//Close Input Streams
		s.close();
		in.close();
	}

	//Print Bank Details to Console using toString method
	public void printDetails() {
		for (BankAccountDetails b : bank_accounts) {
			System.out.println(b.toString());
		}
	}
}