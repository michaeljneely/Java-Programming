package finalvlc;

@SuppressWarnings("serial")
public class UnSupportedAudioFormatException extends Exception {

	public UnSupportedAudioFormatException(String errorMessage){
		super(errorMessage);
	}
}
