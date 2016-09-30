//Michael Neely 13100590 3BCT

package assignment1;

import java.util.Date;

//Abstract base class Employee
public abstract class Employee {

	private String firstName;
	private String lastName;
	private Date joinDate;
	private int employeeNumber;
	// Static to help automatically assign each new employee a unique
	// (incremental) id number
	private static int employeeNumberPool = 100000;

	// constructor
	public Employee(String first, String last, Date d) throws invalidDateException {
		// Set Name and Employee Number
		firstName = first;
		lastName = last;
		// Check if Date Before 1990
		Date before1990 = new Date(90, 1, 1, 0, 0, 0);
		// If Before, Throw Exception
		if ((d.after(new Date())) || d.before(before1990)) {
			throw new invalidDateException(firstName, lastName, d);
		}
		// Else Set Valid Join Date
		else {
			joinDate = d;
			employeeNumber = employeeNumberPool++;
		}
	}

	// get first name
	public String getFirstName() {
		return firstName;
	}

	// get last name
	public String getLastName() {
		return lastName;
	}

	// get Date
	public Date getDate() {
		return joinDate;
	}

	// get Employee Number
	public int getEmployeeNumber() {
		return employeeNumber;
	}

	// Return Full Name as String
	public String toString() {
		return firstName + ' ' + lastName;
	}

	public abstract double earnings();
}
