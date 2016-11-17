package assignment4;

//Author: Michael Neely 13100590 3BCT
//CT 236 Assignment 4 - October 9th, 2016
public class Rational implements Comparable<Rational> {

	private int p; //Numerator
	private int q; //Denominator
	
	//A rational no. is a number that can be expressed in the form of p/q, where p,q are integers with q !=0 and p and q have no common divisor
	//Constructor generates a minimal rational number from Numerator P and Denominator Q
	public Rational(int p, int q)throws IllegalArgumentException {
		//q cannot be 0 as per definition of rational number
		if(q == 0) throw new IllegalArgumentException("Denominator cannot be 0 for a rational number.");
		else{
			//Get Greatest Common Denominator
			int g = gcd(p,q);
			//If GCD is not 1, then they have a common divisor
			if(g != 1){
				//Have to divide p and q by GCD to get minimized form
				p /= g;
				q /= g;
			}
			this.p = p;
			this.q = q;
			//If denominator is negative, make it positive and numerator negative
			 if (q < 0) { 
				 q = -q; 
				 p = -p; 
			 }
		}
	}
	
	//Get Greatest Common Denominator Recursively
	private int gcd(int x, int y) {
	  if(x == 0 || y == 0) return Math.abs(x+y); //base case
	  return gcd(y,x%y);
	}
	
	//Overridden Compare To Method
	//Use Cross Multiplication to Compare
	public int compareTo(Rational r2) {
		Rational r1 = this;
		//Left hand side of cross multiple
		int left = r1.getP() * r2.getQ();
		//Right hand side of cross multiple
		int right = r1.getQ()* r2.getP();
		//If left is greater than right, current rational (r1) is greater
		if(left > right) return 1;
		//If left is less than right, other rational (r2) is greater
		else if (left < right) return -1;
		//Otherwise they are equal
		else return 0;
	}
	
	//Override to String Method -> Return String Representation of Rational
	public String toString(){
		//If denominator is 1, just print out numerator
		//Otherwise print out 'p/q'
		return (q == 1) ? "" + p : p + "/" + q;
	}

	//Getters and Setters
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getQ() {
		return q;
	}
	public void setQ(int q) {
		this.q = q;
	}
}
