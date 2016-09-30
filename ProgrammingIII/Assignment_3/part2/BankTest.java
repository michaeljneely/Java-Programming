package part2;

//CT326 - Programming III
//Author - Michael Neely 13100590

import java.io.IOException;

public class BankTest {

	// Arrays of data to create a few bank accounts
	private static String[] names = { "Michael Neely", "Ross Daly", "Alan Fahey",
			"James Bond", "Harry Potter", "Jason Bourne" };
	private static double[] balances = { 13505.67, 154777.99,250000.14,1000000.00,45142.45,5000000.54};

	//Create New Bank, Write Bank to File and Read Bank back in (using overridden methods)
	//Finally: Print out Account Details
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Bank b = new Bank(names, balances);
		b.writeBank();
		System.out.println("=======================");
		b.readBank();
		System.out.println("=======================\nFinal Results:\n\n");
		b.printDetails();
	}
}
