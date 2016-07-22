package finalvlc;

public class WMAPlugin implements Plugin {

	private String format = "wma";
	
	@Override
	public void play(AudioFile audio) throws UnSupportedAudioFormatException {
		if(audio.getAudioType().equals(format)){
			System.out.println("Playing wma file: " + audio.getFileName());
		}
		else throw new UnSupportedAudioFormatException("Unsupported Audio Format: " + audio.getAudioType());

	}

}
