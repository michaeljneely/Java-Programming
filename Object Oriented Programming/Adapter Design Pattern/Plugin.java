package finalvlc;

public interface Plugin{
	public abstract void play(AudioFile audio) throws UnSupportedAudioFormatException;
}
