//Michael Neely 13100590 3BCT

package assignment1;

import java.util.Date;

//Boss class derived from Employee.

public final class Boss extends Employee {

	private double weeklySalary;

	// constructor for class Boss
	public Boss(String first, String last, double salary, Date joinDate) throws invalidDateException {
		super(first, last, joinDate); // call superclass constructor
		setWeeklySalary(salary);
	}

	// set Boss's salary
	public void setWeeklySalary(double salary) {
		weeklySalary = (salary > 0 ? salary : 0);
	}

	// get Boss's pay
	public double earnings() {
		return weeklySalary;
	}

	// get String representation of Boss's name
	public String toString() {
		return "Boss: " + super.toString();
	}
} // end class Boss