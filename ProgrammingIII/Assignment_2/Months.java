package assignment2;
//Programing III Assignment 2
//Author: Michael Neely 13100590
public class Months {

	public enum Month {
		JANUARY(1, 31), FEBRUARY(2, 28), MARCH(3, 31), APRIL(4, 30), MAY(5, 31), JUNE(6, 30), JULY(7, 31), AUGUST(8,
				31), SEPTEMBER(9, 30), OCTOBER(10, 31), NOVEMBER(11, 30), DECEMBER(12, 31);

		private final int calendar_position; //calendar number of month
		private final int number_of_days; //number of days in month

		//Enum Constructor
		Month(int pos, int days) {
			this.calendar_position = pos;
			this.number_of_days = days;
		}

		//Getters
		public int getDays() {
			return this.number_of_days;
		}

		public int getPosition() {
			return this.calendar_position;
		}

		//Print Out Enum Details in String
		public String toString() {
			return this.name() + " is calendar month " + this.calendar_position + " and has " + this.number_of_days
					+ " days \n";
		}
	}

	//Leap Year Integer
	private static int LEAP_YEAR;

	//Check if Leap Year
	public void leapYearCheck(int year) {
		if(year % 4 != 0) LEAP_YEAR = 0;
		else if (year % 100 != 0) LEAP_YEAR = 1;
		else if (year % 400 != 0) LEAP_YEAR = 0;
		else LEAP_YEAR = 1;
	}

	//Print out number of days in given year
	public String printNumDays(int year) {
		int days = 0;
		//Add all days from months
		for (Month m : Month.values()) {
			days += m.getDays();
		}
		//Check If Leap Year
		leapYearCheck(year);
		String leap_year = (LEAP_YEAR == 1) ? " is a leap year" : " is not a leap year";
		//Add an extra day if it is a leap year
		days += LEAP_YEAR;
		//Print out years and number of days
		return year + leap_year + " and has " + days + " days.\n";
	}

	//Print out months that have exactly the specified number of days
	public String printSpecific(int days) {
		String output = "The months with " + days + " days are: \n";
		//Only add months with specified number of days to output
		for (Month m : Month.values()) {
			if (m.getDays() == days) {
				output += m;
			}
		}
		return output;
	}
	
	//Print out all months, using their toString() methods
	public String printMonths() {
		String output = "";
		for (Month m : Month.values()) {
			output += m.toString();
		}
		return output;
	}
}