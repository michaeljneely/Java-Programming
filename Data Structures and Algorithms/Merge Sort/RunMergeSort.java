package mergesort;

public class RunMergeSort {

	public static void main(String[] args) {
		
		int[] numbers = {17,14,13,11,0,8,79};
		MergeSort a = new MergeSort();
		a.sort(numbers);
		for(int i=0; i<numbers.length; i++){
			System.out.print(numbers[i] + " ");
		}

	}

}
