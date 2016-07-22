package llistqueue;

//Creates a Linked List Implementation of a Queue
public class LLQueue implements Queue{

	protected SLinkedList ll;
	
	//Create a new instance of an empty linked list
	public LLQueue(){
		ll = new SLinkedList();
	}
	
	///Enqueue Object
	@Override
	public void enqueue(Object n) {
		//Insert object at the end of the list
		if (ll.gotoTail()){
			ll.insertNext(n);
		}
		//If it cannot go to the tail then the list is empty, so the enqueued element becomes the head.
		else ll.insertHead(n);
	}

	//Dequeue Object
	@Override
	public Object dequeue() {
		//Dequeue object at the front of the list
		if(ll.gotoHead()){
			//Delete that node from the list and return the object
			Object temp = ll.getCurr();
			ll.deleteHead();
			return temp;
		}
		//If it cannot go to the head then the list is empty, so there is nothing to dequeue
		return null;
	}

	//Check if queue is empty
	@Override
	public boolean isEmpty() {
		return ll.isEmpty();
	}
	
	//Check if queue is full
	@Override
	public boolean isFull() {
		//Can't be full as per instructions
		return false;
	}

	//Return front object of list without removing it
	@Override
	public Object front() {
		//If it can go to the head of the list
		if(ll.gotoHead()){
			//Return the object in that node
			return ll.getCurr();
		}
		//Otherwise there is nothing to return
		return null;
	}

	//Get linked list size
	public long getSize(){
		return ll.size();
	}

}
