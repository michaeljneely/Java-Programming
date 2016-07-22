package finalvlc;

import java.util.HashMap;

public class PluginAdapter implements Plugin {

	private HashMap<String, Plugin> plugins = new HashMap<String, Plugin>();
	private Plugin player;
	
	@Override
	public void play(AudioFile audio) throws UnSupportedAudioFormatException{
		this.player =  plugins.get(audio.getAudioType());
		if (player == null){
			throw new UnSupportedAudioFormatException("Unsupported Audio Type: " + audio.getAudioType());
		}
		this.player.play(audio);
	}
	
	public void registerPlugin(String audioType, Plugin p){
		plugins.put(audioType, p);
	}
}
