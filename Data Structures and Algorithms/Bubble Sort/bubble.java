package bubblesort;

public class bubble {
	
	public static void main(String[] args) {
		int numbers[] = {1,16,93,45,64,21,8};
		bubbleSortAsc(numbers);
		System.out.println("Sorted Array: ");
		for(int i = 0; i < numbers.length; i++){
			System.out.print(numbers[i] + " ");
		}
		

	}
	private static void bubbleSortAsc(int[] nums){
		int j;
		boolean flag = true;
		int temp;
		
		while(flag){
			flag = false;
			for(j=0;j<nums.length-1;j++){
				if(nums[j] > nums[j+1]){
					temp = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = temp;
					flag = true;
				}
			}
		}
	}

}
