package part2;

//CT326 - Programming III
//Author - Michael Neely 13100590

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//Read and Write Bank Accounts from Text File
public class Bank {

	// Array List of Bank Account Details
	public ArrayList<BankAccountDetails> bank_accounts;

	// Constructor. Initialize Bank with data from above arrays
	public Bank(String[] names, double[] balances) {
		bank_accounts = new ArrayList<BankAccountDetails>();
		for (int i = 0; i < names.length; i++) {
			bank_accounts.add(new BankAccountDetails(names[i], balances[i]));
		}
	}

	//Read In Bank using BankAccountDetails Overridden ReadObject Method
	@SuppressWarnings("unchecked")
	public void readBank() throws IOException, ClassNotFoundException{
		FileInputStream in = new FileInputStream("bankdetails_2.txt");
		ObjectInputStream s = new ObjectInputStream(in);
		//Read in Array List of Bank Accounts with Overridden Read Method Explicitly Cast
		bank_accounts = (ArrayList<BankAccountDetails>)s.readObject();
		//Close Input Streams
		s.close();
		in.close();
	}

	// WriteBank using BankAccountDetails Overridden WriteObject Method
	public void writeBank() throws IOException {
		FileOutputStream out = new FileOutputStream("bankdetails_2.txt");
		ObjectOutputStream s = new ObjectOutputStream(out);
		//Write out Array List of Bank Accounts with Overridden Write Method
		s.writeObject(bank_accounts);
		//Flush and Close Output Streams
		s.flush();
		s.close();
		out.flush();
		out.close();
	}

	// Print Bank Details to Console
	public void printDetails() {
		for (BankAccountDetails b : bank_accounts) {
			System.out.println(b.toString());
		}
	}
}