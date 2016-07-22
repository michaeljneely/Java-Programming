package bettercasino;

@SuppressWarnings("serial")

//Print Out Error Message
public class BadCardException extends Exception {
	public BadCardException(String msg){
		super(msg);
	}
}