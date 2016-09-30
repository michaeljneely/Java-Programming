//Michael Neely 13100590 3BCT
package assignment1;

import java.util.Date;

//Thrown When Invalid Date Entered into Employee Constructer
public class invalidDateException extends Exception {
	public invalidDateException(String fName, String lName, Date d) {
		// Generate Error String
		super("Join Date Error. Faulty Date \"" + d + "\" for employee " + fName + " " + lName
				+ ". Date must be after 1990 and cannot be in the future.\n----------------------------------------------");
	}
}
