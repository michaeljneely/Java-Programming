package queue;

@SuppressWarnings("serial")
public class QueueEmptyException extends Exception {

	//print out error message
	public QueueEmptyException(String errorMessage){
		super(errorMessage);
	}
}
