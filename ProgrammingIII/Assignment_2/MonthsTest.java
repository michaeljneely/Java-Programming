package assignment2;
//Programing III Assignment 2
//Author: Michael Neely 13100590

public class MonthsTest {

	public static void main(String[] args){
		Months m = new Months();
		//First Print Overview
		System.out.print(m.printMonths());
		System.out.println("-------------------------------------------");
		//Print Months with 28 Days
		System.out.print(m.printSpecific(28));
		System.out.println("-------------------------------------------");
		//Print Months with 30 Days
		System.out.print(m.printSpecific(30));
		System.out.println("-------------------------------------------");
		//Print Month with 31 Days
		System.out.print(m.printSpecific(31));
		System.out.println("-------------------------------------------");
		//Print Number of Days in 2015 and 2016
        //2015 is Not a Leap Year -> so it will say 365 days
		System.out.print(m.printNumDays(2015));
		System.out.println("-------------------------------------------");
        //2016 IS a Leap Year -> so it will say 366 days
		System.out.print(m.printNumDays(2016));
	}
}
