package queue;

public class QueueCircularArray implements Queue{

	protected Object circularQueue[]; //array used to implement the circular queue
	protected int start; //index for start of queue
	protected int end; //index for end of queue
	protected int count; //number of elements in queue
	protected int capacity; //actual capacity of queue
	public static final int CAPACITY = 1000; //default array capacity

	public QueueCircularArray() {
		// default constructor: creates queue with default capacity
		this(CAPACITY);
	}

	public QueueCircularArray(int cap) {
		// this constructor allows you to specify capacity
		capacity = (cap > 0) ? cap : CAPACITY; // Conditional Operator
		start = end = count = 0; //initialize to zero
		circularQueue = new Object[capacity]; //create array with specified capacity
	}

	@Override
	public void enqueue(Object e) throws QueueFullException{
		//throw exception if full
		if(isFull()){
			throw new QueueFullException("Cannot Enqueue Element. The Queue is Full.");
		}
		else{
			circularQueue[end] = e; //add element to end
			end = (++end) % capacity; //increment end index, wrapping around if necessary
			count++; //increment count
		}
	}

	@Override
	public Object dequeue() throws QueueEmptyException{
		//throw exception if empty
		if(isEmpty()){
			throw new QueueEmptyException("Cannot dequeue anything. The Queue is Empty.");
		}
		else{
			Object e = circularQueue[start]; //get element at start index
			circularQueue[start] = null; //wipe that segment
			start = (++start) % capacity; //increment start pointer, wrapping around if necessary
			count--; //decrement count
			return e; //return element
		}
	}

	@Override
	public boolean isEmpty()  {
		//If the count is zero, there is nothing in the circular queue
		return (count == 0);
	}

	@Override
	public boolean isFull() {
		//If the count equals the length, it is full
		return (count == circularQueue.length);
	}

	@Override
	public Object front() throws QueueEmptyException{
		//throw exception if empty
		if(isEmpty()){
			throw new QueueEmptyException("There is nothing at the front. The Queue is Empty.");
		}
		else{
			//otherwise return front element
			return circularQueue[start];
		}
	}
}
