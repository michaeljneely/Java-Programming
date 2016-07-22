package queue;

@SuppressWarnings("serial")
public class QueueFullException extends Exception {

	//print out error message
	public QueueFullException(String errorMessage){
		super(errorMessage);
	}
}
