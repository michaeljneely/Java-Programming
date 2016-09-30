//Michael Neely 13100590 3BCT

package assignment1;

//Driver for Employee hierarchy

//Java core packages
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

//Java extension packages
import javax.swing.JOptionPane;

/*
 * Employees To Test
 * Boss -> Invalid Date
 * Boss2 -> Valid Date
 * Commission Worker -> Valid Date
 * Piece Worker -> Invalid Date
 * Piece Worker2 -> Valid Date
 * Hourly Worker -> Valid Date
 * 
 * Result: 4 Valid and 2 Invalid Employees
 */

public class Test {

	// test Employee hierarchy
	public static void main(String args[]) throws invalidDateException {
		Employee employee; // superclass reference
		String output = ""; // output to JPANE
		// Threshold for $100.00 Bonus -> January 1st, 2011
		Date bonusThreshold = new Date(111, 1, 1, 0, 0, 0);

		// Dates for Test Employees
		// Boss Date (invalid - before 1990)
		Date d1 = new Date(89, 4, 5, 0, 0, 0);
		// Boss #2 Date (valid)
		Date d2 = new Date(107, 3, 9, 0, 0, 0);
		// CommissionWorker Date (valid)
		Date d3 = new Date(95, 7, 7, 0, 0, 0);
		// PieceWorker Date (invalid - in future)
		Date d4 = new Date(118, 2, 2, 0, 0, 0);
		// PieceWorker#2 Date (valid)
		Date d5 = new Date(110, 8, 4, 0, 0, 0);
		// Hourly Worker Date (valid)
		Date d6 = new Date(114, 8, 8, 0, 0, 0);

		// Array List to Hold Valid Employees
		ArrayList<Employee> employees = new ArrayList<Employee>();

		// Add Employees only If Valid Date

		// Add Boss -> Invalid Date
		try {
			Boss boss = new Boss("John", "Smith", 800.0, d1);
			employees.add(boss);
		} catch (invalidDateException e) {
			output += e.getMessage() + "\n";
		}

		// Add Boss -> Valid Date
		try {
			Boss boss2 = new Boss("Greg", "Anderson", 800.0, d2);
			employees.add(boss2);
		} catch (invalidDateException e) {
			output += e.getMessage() + "\n";
		}

		// Add Commission Worker -> Valid Date
		try {
			CommissionWorker commissionWorker = new CommissionWorker("Sue", "Jones", 400.0, 3.0, 150, d3);
			employees.add(commissionWorker);
		} catch (invalidDateException e) {
			output += e.getMessage() + "\n";
		}

		// Add Piece Worker -> Invalid Date
		try {
			PieceWorker pieceWorker = new PieceWorker("Bob", "Lewis", 2.5, 200, d4);
			employees.add(pieceWorker);
		} catch (invalidDateException e) {
			output += e.getMessage() + "\n";
		}

		// Add Piece Worker -> Valid Date
		try {
			PieceWorker pieceWorker2 = new PieceWorker("Ross", "Daly", 2.5, 200, d5);
			employees.add(pieceWorker2);
		} catch (invalidDateException e) {
			output += e.getMessage() + "\n";
		}

		// Add Hourly Worker -> Valid Date
		try {
			HourlyWorker hourlyWorker = new HourlyWorker("Karen", "Price", 13.75, 40, d6);
			employees.add(hourlyWorker);
		} catch (invalidDateException e) {
			output += e.getMessage() + "\n";
		}

		DecimalFormat precision2 = new DecimalFormat("0.00");

		// Loop Through All Valid Employees
		for (Employee e : employees) {
			// Monthly Salary
			double original = e.earnings() * 4.0;
			// Monthly Salary + possible bonus
			double payout = e.earnings() * 4.0;
			String bonus = "";
			// If Elligible for Bonus
			if (e.getDate().before(bonusThreshold)) {
				bonus = " plus a loyalty bonus of $100.00 ";
				// Add $100.00 bonus
				payout += 100.00;
			}
			// Add Employee String to Output
			output += e.toString() + "(Employee Number: " + e.getEmployeeNumber() + "- Join Date: "
					+ e.getDate().toString() + ") \nearned a weekly salary of $" + precision2.format(e.earnings())
					+ " for a monthly salary of " + precision2.format(original) + bonus + " for a total of "
					+ precision2.format(payout) + "\n----------------------------------------------\n";
		}

		// Generate Message Dialog and Output Results
		JOptionPane.showMessageDialog(null, output, "Payroll Results", JOptionPane.INFORMATION_MESSAGE);

		System.exit(0);
	}
} // end class Test
