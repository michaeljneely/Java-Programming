package assignment4;

import java.util.Comparator;

public class Analysis {

	private static int moves = 0;
	private static int compares = 0;

	public static void main(String[] args){
		String arr[] = {"a", "hello", "x", "w", "q", "h", "d", "p", "a1", "x2", "w2", "q1", "2h", "2d", "3p", "1a",
				"3x", "2w", "3q", "h4", "d4", "4p", "3a", "3x", "5w", "q5", "h5", "d5", "p5",
				"n3x", "2bw", "n3q", "nh4", "nd4", "n4p", "b3a", "b3x", "c5w", "xq5", "kh5", "dj5", "jp5",
				"3x", "2w", "3q", "h4", "d4", "4p", "3a", "3x", "5w", "q5", "h5", "d5", "p5",
				"q3x", "q2w", "3qq", "qh4", "ad4", "4ap", "3aa", "3ax", "a5w", "q5", "fh5", "fd5", "fp5",
				"n3x", "2bw", "n3q", "nh4", "nd4", "n4p", "b3a", "b3x", "c5w", "xq5", "kh5", "dj5", "jp5",
				"3x", "2w", "3q", "h4", "d4", "4p", "3a", "3x", "5w", "q5", "h5", "d5", "p5",
				"n3x", "2bw", "n3q", "nh4", "nd4", "n4p", "b3a", "b3x", "c5w", "xq5", "kh5", "dj5", "jp5",
				"q3x", "q2w", "3qq", "qh4", "ad4", "4ap", "3aa", "3ax", "a5w", "q5", "fh5", "fd5", "fp5",
				"n3x", "2bw", "n3q", "nh4", "nd4", "n4p", "b3a", "b3x", "c5w", "xq5", "kh5", "dj5", "jp5",
				"3x", "2w", "3q", "h4", "d4", "4p", "3a", "3x", "5w", "q5", "h5", "d5", "p5",
				"n3x", "2bw", "n3q", "nh4", "nd4", "n4p", "b3a", "b3x", "c5w", "xq5", "kh5", "dj5", "jp5",
				"3x", "2w", "3q", "h4", "d4", "4p", "3a", "3x", "5w", "q5", "h5", "d5", "p5",
				"q3x", "q2w", "3qq", "qh4", "ad4", "4ap", "3aa", "3ax", "a5w", "q5", "fh5", "fd5", "fp5",
				"n3x", "2bw", "n3q", "nh4", "nd4", "n4p", "b3a", "b3x", "c5w", "xq5", "kh5", "dj5", "jp5",
				"n3x", "2bw", "n3q", "nh4", "nd4", "n4p", "b3a", "b3x", "c5w", "xq5", "kh5", "dj5", "jp5",
				"3x", "2w", "3q", "h4", "d4", "4p", "3a", "3x", "5w", "q5", "h5", "d5", "p5",
				"3x", "2w", "3q", "h4", "d4", "4p", "3a", "3x", "5w", "q5", "h5", "d5", "p5",
				"g3x", "g2w", "f3q", "fh4", "gd4", "f4p", "f3a", "d3x", "s5w", "sq5", "sh5", "dd5", "sp5"};
		
		//String arr2[] = (String[])arr.clone();
		//String arr3[] = (String[])arr.clone();
		//quickSort(arr, new StringComparator());
		//insertionSort(arr2);
		//shellSort(arr3);
		//System.out.println("Quick Sort\n--------------\nNum Comparisons: " + quickCompare + "\nNum Moves: "  + quickMove + "\n\n");
		//insertionSort(arr2);
		//System.out.println("Insertion Sort\n--------------\nNum Comparisons: " + insertCompare + "\nNum Moves: "  + insertMove + "\n\n");
		//insertMove = 0;
		//insertCompare = 0;
		//shellSort(arr3);
		//System.out.println("Shell Sort\n--------------\nNum Comparisons: " + insertCompare + "\nNum Moves: "  + insertMove + "\n\n");
		//String[] rand = randomStringArray(10);
		//System.out.println(array2String(rand));
		Performance(5000);
		Performance(10000);
		Performance(25000);
		Performance(50000);
		Performance(100000);
		
	}

	/** Helper function for shellSort method, that sorts a subarray
	 * that goes from the first to last index, in steps specified by gap.
	 */	
	private static void insertionSort(Comparable[] a, int first, int last, int gap)                                             
	{
		int index;     // general index for keeping track of a position in array
		int toSort;  // stores the index of an out-of-place element when sorting.

		// NOTE: Instead of considering a full array of adjacent elements, we are considering 
		// a sub-list of elements from 'first' to 'last, separated by 'gap'. All others are ignored.
		//
		// Work forward through the list, starting at 2nd element, 
		// and sort each element relative to the ones before it.

		for (toSort = first+gap; toSort <= last; toSort += gap)
		{
			Comparable toSortElement = a[toSort];

			// Go back through the list to see how far back (if at all)
			// this element should be moved.
			// Note: we assume all elements before this are sorted relative to each other.
			boolean moveMade = false;
			index = toSort - gap;
			while ((index >= first) && (toSortElement.compareTo(a[index]) < 0))
			{
				compares++;
				// Shuffle elements over to the right, put firstUnsorted before them 
				a[index+gap] = a[index];
				moves++;
				index = index - gap;
				moveMade = true;
			} 
			if (moveMade) {
				//System.out.println("Inserting " + toSortElement + " at pos " + (index+1));
				a[index+gap] = toSortElement;
				moves++;
			}
		} 
	} 

	/** Version of insertionSort method that uses the correct settings to sort an entire array
	 *  from start to end in steps of 1.
	 *  
	 *  This version uses the 'helper function' version, but is a direct substitute for the 
	 *  quickSort() method call.
	 */
	public static void insertionSort(Comparable arr[])
	{
		insertionSort(arr, 0, arr.length-1, 1);
	}

	/** Task: Sorts equally spaced elements of an array into ascending order.
	 *  The paramater arr is an array of Comparable objects.
	 */

	public static void shellSort (Comparable[] arr)   
	{
		int last = arr.length-1; 

		// Begin with gap = half length of array; reduce by half each time.
		for (int gap = arr.length/2; gap > 0; gap = gap/2)
		{
			if (gap % 2 == 0) gap++; // if gap is even, move to next largest odd number

			// Apply Insertion Sort to the subarrays defined by the gap distance
			for (int first = 0; first < gap; first++) {
				insertionSort (arr, first, last, gap);
			}
		} // end for
	} // end shellSort



	/** QuickSort method:
	 * Sorts the elements of array arr in nondecreasing order according
	 * to comparator c, using the quick-sort algorithm. Most of the work
	 * is done by the auxiliary recursive method quickSortStep.
	 **/
	public static void quickSort (Object[] arr, Comparator c) {
		if (arr.length < 2) return; // the array is already sorted in this case
		quickSortStep(arr, c, 0, arr.length-1); // call the recursive sort method
	}

	/** QuickSortStep method: 
	 * Method called by QuickSort(), which sorts the elements of array s between
	 * indices leftBound and rightBound, using a recursive, in-place,
	 * implementation of the quick-sort algorithm.
	 **/
	private static void quickSortStep (Object[] s, Comparator c,
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
				compares++;
			}
				
			// scan leftward to find an element smaller than the pivot
			while ( (downIndex >= upIndex) && (c.compare(s[downIndex], pivotValue)>=0)){
				downIndex--;
				compares++;
			}
				
			if (upIndex < downIndex) { // both elements were found
				temp = s[downIndex]; 
				s[downIndex] = s[upIndex]; // swap these elements
				s[upIndex] = temp;
				moves += 3;
			}
		} // the loop continues until the indices cross

		int pivotIndex = upIndex;
		temp = s[rightBound]; // swap pivot with the element at upIndex
		s[rightBound] = s[pivotIndex]; 
		s[pivotIndex] = temp; 
		moves += 3;

		// the pivot is now at upIndex, so recursively quicksort each side
		quickSortStep(s, c, leftBound, pivotIndex-1);
		quickSortStep(s, c, pivotIndex+1, rightBound);
	}


	/** utility method to return string representation of array of strings */
	private static String array2String(String[] a)
	{
		String text="[";
		for (int i=0; i<a.length; i++) {
			text += a[i];
			if (i<a.length-1)
				text += ",";
		}
		text += "]";
		return text;
	}
	/*
	Write a method that returns a reference to an array of objects such as Strings or Integers, with 
	the size of the array specified as a parameter to the method. Each object in the array must be 
	initialised to a random value (see Note 3). */

	private static String[] randomStringArray(int size){
		String[] a = new String[size];
		for(int i = 0; i < size; i++){
			String d = Double.toString(Math.random());
			a[i] = d;
		}
		return a;
	}
	
	//Performs 3 Trials for Each Sorting Algorithm and Averages the Results
	public static void Performance(int size){
		long shellMoves = 0;
		long shellCompares = 0;
		long quickMoves = 0;
		long quickCompares = 0;
		long insertMoves = 0;
		long insertCompares = 0;
		
		String[] shell1 = randomStringArray(size);
		String[] shell2 = (String[])shell1.clone();
		String[] shell3 = (String[])shell1.clone();
		
		String[] quick1 = (String[])shell1.clone();
		String[] quick2 = (String[])shell1.clone();
		String[] quick3 = (String[])shell1.clone();
		
		String[] insert1 = (String[])shell1.clone();
		String[] insert2 = (String[])shell1.clone();
		String[] insert3 = (String[])shell1.clone();
		
		long startTime = System.currentTimeMillis();
		shellSort(shell1);
		long endTime = System.currentTimeMillis();
		long shellTrial1Time = endTime - startTime;
		shellMoves += moves;
		shellCompares += compares;
		moves = 0;
		compares = 0;
		
		startTime = System.currentTimeMillis();
		shellSort(shell2);
		endTime = System.currentTimeMillis();
		long shellTrial2Time = endTime - startTime;
		shellMoves += moves;
		shellCompares += compares;
		moves = 0;
		compares = 0;
		
		startTime = System.currentTimeMillis();
		shellSort(shell3);
		endTime = System.currentTimeMillis();
		long shellTrial3Time = endTime - startTime;
		shellMoves += moves;
		shellCompares += compares;
		moves = 0;
		compares = 0;
		shellMoves /= 3;
		shellCompares /= 3;
		
		
		startTime = System.currentTimeMillis();
		quickSort(quick1, new StringComparator());
		endTime = System.currentTimeMillis();
		long quickTrial1Time = endTime - startTime;
		quickMoves += moves;
		quickCompares += compares;
		moves = 0;
		compares = 0;
		
		startTime = System.currentTimeMillis();
		quickSort(quick2, new StringComparator());
		endTime = System.currentTimeMillis();
		long quickTrial2Time = endTime - startTime;
		quickMoves += moves;
		quickCompares += compares;
		moves = 0;
		compares = 0;
		
		
		startTime = System.currentTimeMillis();
		quickSort(quick3, new StringComparator());
		endTime = System.currentTimeMillis();
		long quickTrial3Time = endTime - startTime;
		quickMoves += moves;
		quickCompares += compares;
		moves = 0;
		compares = 0;
		quickMoves /= 3;
		quickCompares /= 3;
		
		startTime = System.currentTimeMillis();
		insertionSort(insert1);
		endTime = System.currentTimeMillis();
		long insertionTrial1Time = endTime - startTime;
		insertMoves += moves;
		insertCompares += compares;
		moves = 0;
		compares = 0;
		
		startTime = System.currentTimeMillis();
		insertionSort(insert2);
		endTime = System.currentTimeMillis();
		long insertionTrial2Time = endTime - startTime;
		insertMoves += moves;
		insertCompares += compares;
		moves = 0;
		compares = 0;
		
		startTime = System.currentTimeMillis();
		insertionSort(insert3);
		endTime = System.currentTimeMillis();
		long insertionTrial3Time = endTime - startTime;
		insertMoves += moves;
		insertCompares += compares;
		moves = 0;
		compares = 0;
		insertMoves /= 3;
		insertCompares /= 3;
		
		
		
		long averageShellTime = (shellTrial1Time + shellTrial2Time + shellTrial3Time) / (3);
		long averageQuickTime = (quickTrial1Time+ quickTrial2Time + quickTrial3Time) / (3);
		long averageInsertionTime = (insertionTrial1Time+ insertionTrial2Time + insertionTrial3Time) / (3);
		
		System.out.println("Average Sorting Algorithm Perfomance for a String Array of size " + size + " Over 3 Trials\n====================");
		System.out.println("Quick Sort\n--------------\nNum Comparisons: " + quickCompares + "\nNum Moves: "  + quickMoves + "\nTime: " + averageQuickTime + "ms\n");
		System.out.println("Insertion Sort\n--------------\nNum Comparisons: " + insertCompares + "\nNum Moves: "  + insertMoves + "\nTime: " + averageInsertionTime+ "ms\n");
		System.out.println("Shell Sort\n--------------\nNum Comparisons: " + shellCompares + "\nNum Moves: "  + shellMoves + "\nTime: " + averageShellTime + "ms\n");
		
	}


}
