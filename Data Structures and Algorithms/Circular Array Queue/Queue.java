package queue;

public interface Queue {

	public void   enqueue(Object n) throws QueueFullException; // add an object at the end of the queue, throw exception if full
	public Object dequeue() throws QueueEmptyException;	// remove an object from the front of the queue, throw exception if empty
	public boolean isEmpty(); // true if queue is empty
	public boolean isFull(); // true if queue is full (if it has limited storage)
	public Object  front()  throws QueueEmptyException; // examine front object on queue without removing it, throw exception if empty
	
}
