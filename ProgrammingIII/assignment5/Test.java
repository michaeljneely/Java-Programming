package assignment5;

import java.util.Scanner;

//Author: Michael Neely 13100590 3BCT
//CT 236 Assignment 5 - October 13th, 2016
public class Test {
	/*
	 * 1) Build and Display Array List of Rationals
	 * 2) Shuffle and Display Array List of Rationals
	 * 3) Sort and Display Array List of Rationals
	 * 4) Begin User Input to Check List for Rationals
	 */
	
	public static void main(String[] args){
		//Array of Rationals for test
		Rational[] test = {new Rational(3,2), new Rational(7,4), new Rational(1,3), new Rational(20,7), new Rational(-1,5),
				new Rational(15,8), new Rational(-33,28), new Rational(6,5), new Rational(200, 27), new Rational(-1,9)};
		
		//Create new Rational Collection
		RationalCollection rc = new RationalCollection(test);
		
		//Show Pre-Sorted Array List
		System.out.println("Array List of Rationals Before Sorting: ");
		System.out.println(rc.toString());
		
		//Shuffle using Rational Collection Shuffle Method
		rc.shuffle();
		System.out.println("Array List of Rationals After Shuffling: ");
		System.out.println(rc.toString());
		
		//Sort using Rational Collection Sort Method
		rc.sort();
		System.out.println("Array List of Rationals After Sorting: ");
		System.out.println(rc.toString());
		
		
		//Begin User Input to Check for Rationals
		Scanner input = new Scanner(System.in);
		int decision = 0;
		//Continue Asking for Rationals Until the User is Done
		while(decision != -1){
			//Get Rational
			Rational r = getInputRational(input);
			//Check if in List
			System.out.println("\n"+ rc.inList(r));
			//Ask if user wants to continue
			System.out.print("Enter -1 to stop, Any Integer to Continue: ");
			while (!input.hasNextInt()) {
				System.out.print("Not an Integer: ");
			    input.next();
			}
			decision = input.nextInt();
		}
	}
	
	//Get Rational From User Input
	public static Rational getInputRational(Scanner in) throws IllegalArgumentException {
		//Get Numerator
		System.out.print("\nEnter Numerator: ");
		//Ask until user inputs and integer
		while (!in.hasNextInt()) {
			System.out.print("Not an Integer. Please Input Numerator Again: ");
		    in.next();
		}
		//Get Denominator
		int p = in.nextInt();
		System.out.print("Enter Denominator: ");
		while (!in.hasNextInt()) {
			System.out.print("Not an Integer. Please Input Denominator Again: ");
		    in.next();
		}
		int q = in.nextInt();
		//Denominator cannot be 0, force to 1
		if(q ==0){
			System.out.println("Denominator cannot be 0, changing to 1...");
			q = 1;
		}
		//Try and Create Rational to Return
		try{
			Rational r = new Rational(p,q);
			return r;
		}catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
