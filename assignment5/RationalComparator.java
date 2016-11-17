package assignment5;

import java.util.Comparator;

//Author: Michael Neely 13100590 3BCT
//CT 236 Assignment 5 - October 13th, 2016
public class RationalComparator implements Comparator<Rational>{

	//Compare Two Rational Objects
	public int compare(Rational r1, Rational r2) {
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

}
