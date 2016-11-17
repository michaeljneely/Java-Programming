package assignment5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Author: Michael Neely 13100590 3BCT
//CT 236 Assignment 5 - October 13th, 2016
public class RationalCollection {

	//Stored list of rationals
	private List<Rational> collection;
	
	//Constructor - Take in List of Rationals
	public RationalCollection(Rational[] list ){
		this.collection = Arrays.asList(list);
	}
	
	//Sort the Array List using the Collections Sort and An Instance of Rational Comparator
	public void sort(){
		Collections.sort(collection, new RationalComparator());
	}
	
	//Shuffle The Array List
	public void shuffle(){
		Collections.shuffle(collection);
	}
	
	//Check If Given Rational in List
	public String inList(Rational r) {
		int position = Collections.binarySearch(collection, r, new RationalComparator());
		return (position >= 0) ? "Rational " + r.toString()  + " found at position " + position : "Rational " + r.toString() + " not found";
	}
	
	//Overrriden to String -> Returns String Representation of the Array List
	public String toString(){
		String output = "";
		for(Rational r: collection) {
			output += r.toString() + "\t";
		}
		return output;
	}
}
