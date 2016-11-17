package assignment4;

import java.util.Arrays;
import java.util.List;

//Author: Michael Neely 13100590 3BCT
//CT 236 Assignment 4 - October 9th, 2016
public class RationalCollection {

	//Stored list of rationals
	private List<Rational> collection;
	
	//Constructor - Take in List of Rationals
	public RationalCollection(Rational[] list ){
		this.collection = Arrays.asList(list);
	}
	
	//Sort the Array List using the Insertion Sort defined below
	public void sort(){
		insertionSort(collection);
	}
	
	
	//Array List Insertion Sort - Ascending Order
	//Psuedo Code - from Wikipedia
	/*
	for i = 1 to length(A)-1
    	j = i
    	while j > 0 and A[j-1] > A[j]
        	swap A[j] and A[j-1]
        	j = j - 1
    	end while
	end for
	 */
	private void insertionSort(List<Rational> list){
        int size = list.size(); //get size
        //Work forward through the list and sort each element relative to the ones before it.
        for (int i = 0; i <= size-1; i++) {
            Rational toSort = list.get(i); //Current value being compared
            int j = i; //set index
            // Go back through the list to see how far back (if at all) this element should be moved and shuffle elements
            while (j > 0 && list.get(j-1).compareTo(toSort) > 0){
                list.set(j, list.get(j - 1)); //shuffle
                j--; //next position
            }
            list.set(j, toSort); //insert
        }
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
