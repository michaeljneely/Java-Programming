package finalvlc;

public class OGGPlugin implements Plugin {

	private String format = "ogg";
	
	@Override
	public void play(AudioFile audio) throws UnSupportedAudioFormatException {
		if(audio.getAudioType().equals(format)){
			System.out.println("Playing ogg file: " + audio.getFileName());
		}
		else throw new UnSupportedAudioFormatException("Unsupported audio type: " + audio.getAudioType());
	}

}
