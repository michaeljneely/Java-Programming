package assignment5;

import java.util.Comparator;
import java.util.Random;

public class QuickSortAnalysis {

	//Number of Moves and Comparisons for Each Implementation of the Quick Sort
	private static long oldMoves, oldCompares, newMoves, newCompares = 0;

	//Run The Analysis
	public static void main(String[] args){
		
		int trials[] = {100,500,2500,5000,7500}; //Number of elements in each array
		
		for(int i = 0; i < trials.length; i++){
			randomPerformance(trials[i]); //Test Performance on Unsorted Array
			ascendingPerformance(trials[i]); //Test Performance on Array Sorted in Ascending Order
			descendingPerformance(trials[i]); //Test Performance on Array Sorted in Descending Order
		}
	}

	/** Old Quick Sort -- From Notes **/

	public static void oldQuickSort (Object[] arr, Comparator c) {
		if (arr.length < 2) return; // the array is already sorted in this case
		oldQuickSortStep(arr, c, 0, arr.length-1); // call the recursive sort method
	}

	private static void oldQuickSortStep (Object[] s, Comparator c,
			int leftBound, int rightBound ) 
	{
		if (leftBound >= rightBound){
			return; // the indices have crossed
		}
		Object temp;  // temp object used for swapping

		// Set the pivot to be the last element
		Object pivotValue = s[rightBound];

		// Now partition the array 
		int upIndex = leftBound;     // will scan rightward, 'up' the array
		int downIndex = rightBound-1; // will scan leftward, 'down' the array
		while (upIndex <= downIndex) 
		{ 
			// scan right until larger than the pivot
			while ( (upIndex <= downIndex) && (c.compare(s[upIndex], pivotValue)<=0) ){
				upIndex++; 
				oldCompares++;
			}

			// scan leftward to find an element smaller than the pivot
			while ( (downIndex >= upIndex) && (c.compare(s[downIndex], pivotValue)>=0)){
				downIndex--;
				oldCompares++;
			}

			if (upIndex < downIndex) { // both elements were found
				temp = s[downIndex]; 
				s[downIndex] = s[upIndex]; // swap these elements
				s[upIndex] = temp;
				oldMoves += 3;
			}
		} // the loop continues until the indices cross

		int pivotIndex = upIndex;
		temp = s[rightBound]; // swap pivot with the element at upIndex
		s[rightBound] = s[pivotIndex]; 
		s[pivotIndex] = temp; 
		oldMoves += 3;

		// the pivot is now at upIndex, so recursively quicksort each side
		oldQuickSortStep(s, c, leftBound, pivotIndex-1);
		oldQuickSortStep(s, c, pivotIndex+1, rightBound);
	}


	/** New  Quick Sort -- Better Choice of Pivot **/


	public static void newQuickSort (Object[] arr, Comparator c) {
		if (arr.length < 2) return; // the array is already sorted in this case
		newQuickSortStep(arr, c, 0, arr.length-1); // call the recursive sort method
	}
	private static void newQuickSortStep (Object[] s, Comparator c,
			int leftBound, int rightBound ) 
	{
		Random rand = new Random();
		
		if (leftBound >= rightBound){
			return; // the indices have crossed
		}
		Object temp;  // temp object used for swapping

		/** NEW
		 * Set the pivot to be the middle of three random elements in the array**/

		//Get middle of 3 random indices
		int rand1 = rand.nextInt(rightBound + 1); //To get random int in range min-> max : rand.nextInt((max - min) + 1) + min;
		int rand2 = rand.nextInt(rightBound + 1);
		int rand3 = rand.nextInt(rightBound + 1);
		int middle = median(rand1,rand2,rand3);
		
		//Swap this 'middle' element with right bound
		temp = s[rightBound];
		s[rightBound] = s[middle];
		s[middle] = temp;
		newMoves += 3;// 3 new moves made
		
		/** END NEW **/
		
		// Set the pivot to be the last element
		Object pivotValue = s[rightBound];

		// Now partition the array 
		int upIndex = leftBound;     // will scan rightward, 'up' the array
		int downIndex = rightBound-1; // will scan leftward, 'down' the array
		while (upIndex <= downIndex) 
		{ 
			// scan right until larger than the pivot
			while ( (upIndex <= downIndex) && (c.compare(s[upIndex], pivotValue)<=0) ){
				upIndex++; 
				newCompares++;
			}

			// scan leftward to find an element smaller than the pivot
			while ( (downIndex >= upIndex) && (c.compare(s[downIndex], pivotValue)>=0)){
				downIndex--;
				newCompares++;
			}

			if (upIndex < downIndex) { // both elements were found
				temp = s[downIndex]; 
				s[downIndex] = s[upIndex]; // swap these elements
				s[upIndex] = temp;
				newMoves += 3;
			}
		} // the loop continues until the indices cross

		int pivotIndex = upIndex;
		temp = s[rightBound]; // swap pivot with the element at upIndex
		s[rightBound] = s[pivotIndex]; 
		s[pivotIndex] = temp; 
		newMoves += 3;

		// the pivot is now at upIndex, so recursively quicksort each side
		newQuickSortStep(s, c, leftBound, pivotIndex-1);
		newQuickSortStep(s, c, pivotIndex+1, rightBound);
	}

	//Return median of 3 integers
	private static int median(int a, int b, int c) {
		if(a > b){
			if(b > c){
				return b;
			}else if (a > c){
				return c;
			}else{
				return a;
			}
		}else{
			if(a > c){
				return a;
			}else if (b > c){
				return c;
			}else{
				return b;
			}
		}
	}

	//Returns a Random String Array of Specified Size
	private static String[] randomStringArray(int size){
		String[] a = new String[size];
		for(int i = 0; i < size; i++){
			String d = Double.toString(Math.random());
			a[i] = d;
		}
		return a;
	}
	
	/** Insertion Sort to Arrays Before the Ascending/Descending Trials are Run **/
	public static void insertionSort(Comparable arr[])
	{
		insertionSort(arr, 0, arr.length-1, 1);
	}

	private static void insertionSort(Comparable[] a, int first, int last, int gap)                                             
	{
		int index; 
		int toSort;
		for (toSort = first+gap; toSort <= last; toSort += gap)
		{
			Comparable toSortElement = a[toSort];
			boolean moveMade = false;
			index = toSort - gap;
			while ((index >= first) && (toSortElement.compareTo(a[index]) < 0))
			{
				a[index+gap] = a[index];
				index = index - gap;
				moveMade = true;
			} 
			if (moveMade) {
				a[index+gap] = toSortElement;
			}
		} 
	} 

	//Test Both Quick Sort Implementations on Sorted Array in Ascending Order
	public static void ascendingPerformance(int size){
		String[] arr1 = randomStringArray(size);
		insertionSort(arr1);
		String[] arr2 = (String[])arr1.clone();
		performance(arr1,arr2,"Sorted Array in Ascending Order", size);
	}
	
	//Test Both Quick Sort Implementations on Sorted Array in Descending Order
	public static void descendingPerformance(int size){
		String[] arr1 = randomStringArray(size);
		insertionSort(arr1);
		//Put Array in Descending Order
		String[] temp = new String[arr1.length];
		for(int i = 0; i < arr1.length; i++){
			temp[i] = arr1[arr1.length-1-i];
		}
		arr1 = temp;
		String[] arr2 = (String[])arr1.clone();
		performance(arr1,arr2,"Sorted Array in Descending Order", size);
	}
	
	//Test Both Quick Sort Implementations on Unsorted Array
	public static void randomPerformance(int size){
		String[] arr1 = randomStringArray(size);
		String[] arr2 = (String[])arr1.clone();
		performance(arr1,arr2,"Unsorted Array", size);
	}
	
	//Common Method to Test Performance
	public static void performance(String[] arr1, String[] arr2, String type, int size){
		//Reset Counts
		oldCompares = oldMoves = newMoves = newCompares = 0;
		
		//Run Old Implementation and Time
		long startTime = System.currentTimeMillis();
		oldQuickSort(arr1, new StringComparator());
		long endTime = System.currentTimeMillis();
		long oldQuickSortTrialTime = endTime - startTime;
		
		//Run New Implementation and Time
		startTime = System.currentTimeMillis();
		newQuickSort(arr2, new StringComparator());
		endTime = System.currentTimeMillis();
		long newQuickSortTrialTime = endTime - startTime;
		
		//Display Results
		System.out.println(type + " Performance (" + size + " Elements)\n=================================\n");
		System.out.println("Old Quick Sort\n--------------\nNum Comparisons: " + oldCompares + "\nNum Moves: "  + oldMoves + "\nTime: " + oldQuickSortTrialTime + "ms\n");
		System.out.println("New Quick Sort\n--------------\nNum Comparisons: " + newCompares + "\nNum Moves: "  + newMoves + "\nTime: " + newQuickSortTrialTime + "ms\n");
	}
	
	public QuickSortAnalysis(){}
}