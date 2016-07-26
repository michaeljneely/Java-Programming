package assignment4;

/** InsertSort.java: 
 *  
 *  This is extracted from my code in the ShellSort class. 
 *  It is slightly simpler than the insertionSort in the ShellSort class because 
 *  it does not use the startpoint, endpoint and gap.
 */

import javax.swing.JOptionPane;

public class InsertSort
{
	/** 
	 * Insertion Sort method: this one sorts an array from its start to its end.
	 * (You cannot specify a start point, end point or gap.)
	 */	
	private static void insertionSort(Comparable[] a)                                             
	{
		int index;     // general index for keeping track of a position in array
		int toSort;  // stores the index of an out-of-place element when sorting.
		int last = a.length-1;

		// Work forward through the list, starting at 2nd element, 
		// and sort each element relative to the ones before it.
		for (toSort = 1; toSort <= last; toSort++)
		{
			Comparable toSortElement = a[toSort];
			System.out.println("Checking " + a[toSort] + " at pos " + toSort);

			// Go back through the list to see how far back (if at all)
			// this element should be moved.
			// Note: we assume all elements before this are sorted relative
			// to each other.
			boolean moveMade = false; // Minor optimisation, also used in messages displayed
			index = toSort - 1;
			while ((index >= 0) && (toSortElement.compareTo(a[index]) < 0))
			{
				// Shuffle elements over to the right, put firstUnsorted before them 
				System.out.println("Moving forward " + a[index] + " from pos " + index);
				a[index+1] = a[index];
				index--;
				moveMade = true;
			}
			if (moveMade) {
				System.out.println("Inserting " + toSortElement + " at pos " + (index+1));
				a[index+1] = toSortElement;
			}
			else {				
				System.out.println("Leaving " + toSortElement + " at pos " + (index+1));
			}
		} 
	} 

	
	/** Main method to test insertionSort */
	public static void main(String[] args)
	{
		String arr[] = {"W","B","Z","Q","M"};
		//Integer arr[] = {12, 17, 3, 2000, 2001, 99, 203};

		/*
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
        
        */
		
		JOptionPane.showMessageDialog(null, "Array length is "+ arr.length);
		JOptionPane.showMessageDialog(null, 
				"Array before sorting:\n" + array2String(arr));
		insertionSort(arr);
		JOptionPane.showMessageDialog(null, 
				"Array after sorting with Insertion Sort:\n" + array2String(arr));
		
		System.exit(0);
	}

	/** Utility method to return string representation of array of strings */
	private static String array2String(Object[] a)
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

	
}