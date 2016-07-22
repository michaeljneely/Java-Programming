package llistqueue;

//Test Class for the Linked List Implementation of a Queue
public class LLQueueTest {
	
	//Instance of a linked list queue
	static LLQueue lq;
	
	//Dequeue and Display all objects in the queue
	public static void display(){
		int size = (int) lq.getSize();
		for(int i = 0; i < size; i++){
			System.out.println("Node " + i + " contains: " + lq.dequeue().toString());
		}
	}
	
	public static void main(String[] args){
		lq = new LLQueue();
		
		//Stuff to enqueue/dequeue
		String[] words = {"A", "B", "C", "D", "E"};
		int[] numbers = {1,2,3};
		
		//Try to dequeue - Show that you cannot because the list is empty
		if( lq.dequeue() == null) System.out.println("Cannot dequeue anything. The list is empty\n");
		
		//Enqueue 5
		for(String word : words){
			lq.enqueue(word);
			System.out.println(word + " has been enqueued\n");
		}
		
		//Dequeue 2
		for(int i = 0; i < 2; i++){
			String temp = lq.dequeue().toString();
			System.out.println(temp + " has been dequeued\n");
		}
		
		//Enqueue 3
		for(int number : numbers){
			lq.enqueue(number);
			System.out.println(number + " has been enqueued\n");
		}
		
		//Display
		display();
	}
}
