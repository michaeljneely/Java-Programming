package finalvlc;

public class MP3Plugin implements Plugin {

	private String format = "mp3";
	
	@Override
	public void play(AudioFile audio) throws UnSupportedAudioFormatException {
		if(audio.getAudioType().equals(format)){
			System.out.println("Playing mp3 file: " + audio.getFileName());
		}
		else throw new UnSupportedAudioFormatException("UnSupported Audio Type: " + audio.getAudioType());

	}

}
